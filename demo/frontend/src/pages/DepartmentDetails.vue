<template>
  <button class="button" v-if="$store.state.isAddDepartment" @click="addDepartment()">Add</button>
  <button class="button" v-else @click="editDepartment()">Edit</button>
  <button class="button" v-if="!$store.state.isAddDepartment" @click="deleteDepartment()">Delete</button>
  <table>

    <thead>
    <th>Title</th>
    <th>Notes</th>
    <th>Parent_Id</th>

    </thead>

    <tbody>
    <tr>

      <td><input type="text" v-model="title"/></td>
      <td><input type="text" v-model="notes"/></td>
      <td>
        <select v-model="selected">
          <option v-for="department in departments" v-bind:value="department.id">{{ department.id }}</option>
          <option>null</option>
        </select>
      </td>


    </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      departments: [],
      title: '',
      notes: '',
      selected: null,
      id: 0,
    }
  },
  methods: {
    async getDepartments() {
      let response;
      response = await axios.get('http://localhost:8080/api/department/get/all', {
        withCredentials: true,
      })
      this.departments = response.data
    },
    async addDepartment() {
      let response;
      response = await axios.post('http://localhost:8080/api/department/add', {
        notes: this.notes,
        parentId: this.selected,
        title: this.title
      }, {
        withCredentials: true,
      })
      this.$router.push('/department')
    }, updateDataDepartment() {
      if (this.$store.state.department !== null) {
        this.title = this.$store.state.department.title
        this.notes = this.$store.state.department.notes
        this.selected = this.$store.state.department.parentId
        this.id = this.$store.state.department.id
      }
    }, async editDepartment() {
      let response;
      response = await axios.put('http://localhost:8080/api/department/edit?id=' + this.id, {
        notes: this.notes,
        parentId: this.selected,
        title: this.title
      }, {
        withCredentials: true,
      })
      this.$router.push('/department')
    }, async deleteDepartment() {
      let response;
      response = await axios.delete('http://localhost:8080/api/department/delete?id=' + this.id, {
        withCredentials: true,
      })
      this.$router.push('/department')
    }

  }, mounted() {
    this.updateDataDepartment()
    this.getDepartments()
  }
}

</script>

<style scoped>
.button {
  padding: 5px 5px 5px 5px;
  margin: 10px;
}
</style>
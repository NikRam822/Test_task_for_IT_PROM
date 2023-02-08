<template>
  <button class="button" v-if="$store.state.isAddEmployee" @click="addEmployee()">Add</button>
  <button class="button" v-else @click="editEmployee()">Edit</button>
  <button class="button" v-if="!$store.state.isAddEmployee" @click="deleteEmployee()">Delete</button>
  <table>

    <thead>
    <th>Full Name</th>
    <th>Profession</th>
    <th>Department</th>

    </thead>

    <tbody>
    <tr>

      <td><input type="text" v-model="fullName"/></td>
      <td>
        <select v-model="selectedProfession">
          <option v-for="profession in professions" v-bind:value="profession.id">{{ profession.id }}</option>
        </select>
      </td>
      <td>
        <select v-model="selectedDepartment">
          <option v-for="department in departments" v-bind:value="department.id">{{ department.id }}</option>
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
      professions: [],
      departments: [],
      fullName: '',
      selectedProfession: null,
      selectedDepartment: null,
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
    async getProfessions() {
      let response;
      response = await axios.get('http://localhost:8080/api/profession/get/all', {
        withCredentials: true,
      })
      this.professions = response.data
    },
    async addEmployee() {
      let response;
      response = await axios.post('http://localhost:8080/api/employee/add', {
        profession: this.selectedProfession,
        department: this.selectedDepartment,
        fullName: this.fullName
      }, {
        withCredentials: true,
      })
      this.$router.push('/employee')
    }, updateDataEmployee() {
      if (this.$store.state.employee !== null) {
        this.fullName = this.$store.state.employee.fullName
        this.selectedProfession = this.$store.state.employee.profession.id
        this.selectedDepartment = this.$store.state.employee.department.id
        this.id = this.$store.state.employee.id
      }
    }, async editEmployee() {
      let response;
      response = await axios.put('http://localhost:8080/api/employee/edit?id=' + this.id, {
        profession: this.selectedProfession,
        department: this.selectedDepartment,
        fullName: this.fullName
      }, {
        withCredentials: true,
      })
      this.$router.push('/employee')
    }, async deleteEmployee() {
      let response;
      response = await axios.delete('http://localhost:8080/api/employee/delete?id=' + this.id, {
        withCredentials: true,
      })
      this.$router.push('/employee')
    }

  }, mounted() {
    this.updateDataEmployee()
    this.getDepartments()
    this.getProfessions()
  }
}

</script>

<style scoped>
.button {
  padding: 5px 5px 5px 5px;
  margin: 10px;
}
</style>
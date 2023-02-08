<template>
  <div>
    <h1>Отделы</h1>
  </div>

  <table>

    <thead>
    <th>Id</th>
    <th>Title</th>
    <th>Notes</th>
    <th>Parent_Id</th>

    </thead>

    <tbody>
    <tr v-for="key in this.departments">

      <td class="line">
        <button class="button"
                @click="$store.state.department=key,$store.state.isAddDepartment=false,$router.push('/department/details')">
          {{ key.id }}
        </button>
      </td>
      <td class="lineMiddle">{{ key.title }}</td>
      <td class="lineMiddle">{{ key.notes }}</td>
      <td class="line">{{ key.parentId }}</td>

    </tr>
    </tbody>
  </table>
  <button @click="addDepartment()"> Add
    Department
  </button>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      departments: []
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
    addDepartment() {
      if(this.$store.state.department!==null){
        this.$store.state.department.title = ''
        this.$store.state.department.notes = ''
        this.$store.state.department.parentId = null
      }

      this.$store.state.isAddDepartment = true
      this.$router.push('/department/details')
    }
  }, mounted() {
    this.getDepartments()
  }
}


</script>

<style scoped>


.line {
  padding: 10px 10px 10px 10px;
  text-align: center;
}

.button {
  padding: 5px 5px 5px 5px;
}

.lineMiddle {
  padding: 10px 10px 10px 10px;
  text-align: start;
}
</style>

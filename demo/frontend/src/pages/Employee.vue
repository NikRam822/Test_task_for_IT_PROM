<template>
  <div>
    <h1>Сотрудники</h1>
  </div>
  <table>

    <thead>
    <th>Id</th>
    <th>Full Name</th>
    <th>Profession</th>
    <th>Department</th>

    </thead>

    <tbody>
    <tr v-for="key in this.employees">

      <td class="line">
        <button class="button"
                @click="$store.state.employee=key,$store.state. isAddEmployee=false,$router.push('/employee/details')">
          {{ key.id }}
        </button>
      </td>
      <td class="lineMiddle">{{ key.fullName }}</td>
      <td class="lineMiddle">{{ key.profession.id }}</td>
      <td class="line">{{ key.department.id }}</td>

    </tr>
    </tbody>
  </table>
  <button @click="addEmployee()"> Add Employee
  </button>
</template>

<script>
  import axios from "axios";

  export default {
  data() {
  return {
  employees: []
}
},
  methods: {
  async getEmployee() {
  let response;
  response = await axios.get('http://localhost:8080/api/employee/get/all', {
  withCredentials: true,
})
  this.employees = response.data
},
  addEmployee() {
  if(this.$store.state.employee!==null){
  this.$store.state.employee.fullName = ''
  this.$store.state.employee.profession.id = null
  this.$store.state.employee.department.id = null
}

  this.$store.state.isAddEmployee = true
  this.$router.push('/employee/details')
}
}, mounted() {
  this.getEmployee()
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
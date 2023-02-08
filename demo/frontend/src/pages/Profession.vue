<template>
  <div>
    <h1>Профессии</h1>
  </div>
<table>

<thead>
<th>Id</th>
<th>Title</th>
<th>Notes</th>

</thead>

<tbody>
<tr v-for="key in this.professions">

  <td class="line">
    <button class="button"
            @click="$store.state.profession=key,$store.state.isAddProfession=false,$router.push('/profession/details')">
      {{ key.id }}
    </button>
  </td>
  <td class="lineMiddle">{{ key.title }}</td>
  <td class="lineMiddle">{{ key.notes }}</td>

</tr>
</tbody>
</table>
<button @click="addDepartment()"> Add Profession
</button>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      professions: []
    }
  },
  methods: {
    async getProfession() {
      let response;
      response = await axios.get('http://localhost:8080/api/profession/get/all', {
        withCredentials: true,
      })
      this.professions = response.data
    },
    addDepartment() {
      if(this.$store.state.profession!==null){
        this.$store.state.profession.title = ''
        this.$store.state.profession.notes = ''
      }

      this.$store.state.isAddProfession = true
      this.$router.push('/profession/details')
    }
  }, mounted() {
    this.getProfession()
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

<template>
  <button class="button" v-if="$store.state.isAddProfession" @click="addProfession()">Add</button>
  <button class="button" v-else @click="editProfession()">Edit</button>
  <button class="button" v-if="!$store.state.isAddProfession" @click="deleteProfession()">Delete</button>
  <table>

    <thead>
    <th>Title</th>
    <th>Notes</th>

    </thead>

    <tbody>
    <tr>
      <td><input type="text" v-model="title"/></td>
      <td><input type="text" v-model="notes"/></td>

    </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      title: '',
      notes: '',
      id: 0,
    }
  },
  methods: {
    async addProfession() {
      let response;
      response = await axios.post('http://localhost:8080/api/profession/add', {
        notes: this.notes,
        title: this.title
      }, {
        withCredentials: true,
      })
      this.$router.push('/profession')
    }, updateDataProfession() {
      if (this.$store.state.profession !== null) {
        this.title = this.$store.state.profession.title
        this.notes = this.$store.state.profession.notes
        this.id = this.$store.state.profession.id
      }
    }, async editProfession() {
      let response;
      response = await axios.put('http://localhost:8080/api/profession/edit?id=' + this.id, {
        notes: this.notes,
        title: this.title
      }, {
        withCredentials: true,
      })
      this.$router.push('/profession')
    }, async deleteProfession() {
      let response;
      response = await axios.delete('http://localhost:8080/api/profession/delete?id=' + this.id, {
        withCredentials: true,
      })
      this.$router.push('/profession')
    }

  }, mounted() {
    this.updateDataProfession()
  }
}

</script>

<style scoped>
.button {
  padding: 5px 5px 5px 5px;
  margin: 10px;
}
</style>
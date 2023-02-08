import Vue from 'vue'
import Vuex, {createStore} from 'vuex'

const store = createStore({
    state() {
        return {
            department: null,
            isAddDepartment: false,

            employee: null,
            isAddEmployee: false,

            profession: null,
            isAddProfession: false,
        }
    }
})

export default store
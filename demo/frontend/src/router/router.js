import Main from "@/pages/Main";
import {createRouter, createWebHistory} from "vue-router";
import Department from "@/pages/Department";
import Profession from "@/pages/Profession";
import Employee from "@/pages/Employee";
import DepartmentDetails from "../pages/DepartmentDetails.vue";
import EmployeeDetails from "../pages/EmployeeDetails.vue";
import ProfessionDetails from "../pages/ProfessionDetails.vue";

const routes = [
    {
        path: '/',
        component: Main
    },
    {
        path: '/department',
        component: Department
    },
    {
        path: '/profession',
        component: Profession
    },
    {
        path: '/employee',
        component: Employee
    },
    {
    path: '/department/details',
        component: DepartmentDetails
    },{
        path: '/employee/details',
        component: EmployeeDetails
    },{
        path: '/profession/details',
        component: ProfessionDetails
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;

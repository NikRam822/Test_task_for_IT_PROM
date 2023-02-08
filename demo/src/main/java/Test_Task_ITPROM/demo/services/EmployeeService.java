package Test_Task_ITPROM.demo.services;

import Test_Task_ITPROM.demo.model.Department;
import Test_Task_ITPROM.demo.model.Employee;
import Test_Task_ITPROM.demo.model.Profession;

import java.util.List;

public interface EmployeeService {
    Employee add(Employee employee);

    boolean delete(Long id);

    Employee edit(Employee employee);

    List<Employee> getAll();

    Profession findProfessionById(Long id);

    Department findDepartmentById(Long id);
}

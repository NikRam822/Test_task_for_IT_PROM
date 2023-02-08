package Test_Task_ITPROM.demo.services;

import Test_Task_ITPROM.demo.model.Department;

import java.util.List;

public interface DepartmentService {

    Department add(Department department);

    boolean delete(Long id);

    Department edit(Department department);

    List<Department> getAll();

    Department findById(Long id);

    Department findByTitle(String title);
}

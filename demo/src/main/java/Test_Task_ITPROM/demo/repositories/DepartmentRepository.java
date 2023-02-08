package Test_Task_ITPROM.demo.repositories;

import Test_Task_ITPROM.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByTitle(String title);

}

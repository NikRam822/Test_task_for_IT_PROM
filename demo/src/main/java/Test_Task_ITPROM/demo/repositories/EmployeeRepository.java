package Test_Task_ITPROM.demo.repositories;

import Test_Task_ITPROM.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

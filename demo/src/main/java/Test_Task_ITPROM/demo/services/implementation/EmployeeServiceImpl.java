package Test_Task_ITPROM.demo.services.implementation;


import Test_Task_ITPROM.demo.model.Department;
import Test_Task_ITPROM.demo.model.Employee;
import Test_Task_ITPROM.demo.model.Profession;
import Test_Task_ITPROM.demo.repositories.DepartmentRepository;
import Test_Task_ITPROM.demo.repositories.EmployeeRepository;
import Test_Task_ITPROM.demo.repositories.ProfessionRepository;
import Test_Task_ITPROM.demo.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger LOGGER = log;

    private final EmployeeRepository employeeRepository;
    private final ProfessionRepository professionRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProfessionRepository professionRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.professionRepository = professionRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee add(Employee employee) {
        Employee savedEmployee = null;
        try {
            savedEmployee = employeeRepository.save(employee);
            LOGGER.info("Employee saved: {}", savedEmployee);
        } catch (Exception e) {
            LOGGER.error("Employee is not saved: {}", savedEmployee);
        }
        return savedEmployee;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            try {
                employeeRepository.deleteById(id);
                LOGGER.info("Deleted employee with id: {}", id);
                return true;
            } catch (Exception e) {
                LOGGER.info("Employee with id: {}, is not deleted :{}", id, e);
                return false;
            }

        } else {
            LOGGER.warn("Id is null");
            return false;
        }
    }

    @Override
    public Employee edit(Employee employee) {
        Employee editedEmployee = employeeRepository.getReferenceById(employee.getId());

        editedEmployee.setFullName(employee.getFullName());
        editedEmployee.setDepartment(employee.getDepartment());
        editedEmployee.setProfession(employee.getProfession());

        Employee savedEmployee = null;
        try {
            savedEmployee = employeeRepository.save(editedEmployee);
            LOGGER.info("Employee saved: {}", savedEmployee);
        } catch (Exception e) {
            LOGGER.error("Employee is not saved: {}", savedEmployee);
        }

        return savedEmployee;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Profession findProfessionById(Long id) {
        return professionRepository.getReferenceById(id);
    }

    @Override
    public Department findDepartmentById(Long id) {
        return departmentRepository.getReferenceById(id);
    }
}

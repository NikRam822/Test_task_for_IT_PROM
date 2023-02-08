package Test_Task_ITPROM.demo.controllers;

import Test_Task_ITPROM.demo.dto.EmployeeDto;
import Test_Task_ITPROM.demo.model.Department;
import Test_Task_ITPROM.demo.model.Employee;
import Test_Task_ITPROM.demo.model.Profession;
import Test_Task_ITPROM.demo.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final Logger LOGGER = log;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    EmployeeService employeeService;


    @GetMapping("/get/all")
    ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<String> add(@RequestBody EmployeeDto employeeDto) {

        Profession profession = employeeService.findProfessionById(employeeDto.getProfession());
        Department department = employeeService.findDepartmentById(employeeDto.getDepartment());
        if (profession == null || department == null) {
            LOGGER.warn("Department or Profession with this id is not exist");
            return new ResponseEntity<>("Department or Profession with this id is not exist", HttpStatus.CONFLICT);
        }

        Employee employee = new Employee();

        employee.setFullName(employeeDto.getFullName());
        employee.setProfession(profession);
        employee.setDepartment(department);


        Employee savedEmployee = employeeService.add(employee);
        if (savedEmployee != null) {
            return new ResponseEntity<>("Employee added: " + savedEmployee.getFullName(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee is not added", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete")
    ResponseEntity<String> delete( Long id) {
        boolean isDeleted = employeeService.delete(id);
        if (isDeleted) {
            return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee is not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/edit")
    ResponseEntity<String> edit(@RequestBody EmployeeDto employeeDto, Long id) {
        Profession profession = employeeService.findProfessionById(employeeDto.getProfession());
        Department department = employeeService.findDepartmentById(employeeDto.getDepartment());
        if (profession == null || department == null) {
            LOGGER.warn("Department or Profession with this id is not exist");
            return new ResponseEntity<>("Department or Profession with this id is not exist", HttpStatus.CONFLICT);
        }

        Employee employee = employeeService.edit(new Employee(id, employeeDto.getFullName(), profession, department));

        if (employee != null) {
            return new ResponseEntity<>("Employee edited: " + employee.getFullName(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee is not edited: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

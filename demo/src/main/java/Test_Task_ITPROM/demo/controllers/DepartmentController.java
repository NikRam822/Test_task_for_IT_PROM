package Test_Task_ITPROM.demo.controllers;

import Test_Task_ITPROM.demo.dto.DepartmentDto;
import Test_Task_ITPROM.demo.model.Department;
import Test_Task_ITPROM.demo.services.DepartmentService;
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
@RequestMapping("/api/department")
public class DepartmentController {

    private final Logger LOGGER = log;
    DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/get/all")
    ResponseEntity<List<Department>> getAll() {
        return new ResponseEntity<>(departmentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<String> addDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = departmentService.findByTitle(departmentDto.getTitle());

        if (department != null) {
            LOGGER.warn("Department with title {} is already exist", department.getTitle());
            return new ResponseEntity<>("Department with this title is already exist", HttpStatus.CONFLICT);
        }
        department = new Department();
        department.setNotes(departmentDto.getNotes());
        department.setTitle(departmentDto.getTitle());
        department.setParentId(departmentDto.getParentId());

        Department savedDepartment = departmentService.add(department);
        if (savedDepartment != null) {
            return new ResponseEntity<>("Department added: " + savedDepartment.getTitle(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department is not added", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete")
    ResponseEntity<String> delete( Long id) {
        boolean isDeleted = departmentService.delete(id);
        if (isDeleted) {
            return new ResponseEntity<>("Department deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department is not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/edit")
    ResponseEntity<String> edit(@RequestBody DepartmentDto departmentDto, Long id) {
        Department department = departmentService.edit(new Department(id, departmentDto.getTitle(), departmentDto.getNotes(), departmentDto.getParentId()));

        if (department != null) {
            return new ResponseEntity<>("Department edited: " + department.getTitle(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department is not edited: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

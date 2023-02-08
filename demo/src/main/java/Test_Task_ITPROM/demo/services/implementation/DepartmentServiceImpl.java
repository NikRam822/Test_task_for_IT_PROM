package Test_Task_ITPROM.demo.services.implementation;

import Test_Task_ITPROM.demo.model.Department;
import Test_Task_ITPROM.demo.repositories.DepartmentRepository;
import Test_Task_ITPROM.demo.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final Logger LOGGER = log;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department add(Department department) {
        Department savedDepartment = null;
        try {
            savedDepartment = departmentRepository.save(department);
            LOGGER.info("Department saved: {}", savedDepartment);
        } catch (Exception e) {
            LOGGER.error("Department is not saved: {}", savedDepartment);
        }
        return savedDepartment;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            try {
                departmentRepository.deleteById(id);
                LOGGER.info("Deleted department with id: {}", id);
            } catch (Exception e) {
                LOGGER.error("Department with id: {}, is not deleted: ", id, e);
                return false;
            }
            return deleteParentForAll(id);
        } else {
            LOGGER.warn("Id is null");
            return false;
        }

    }

    private boolean deleteParentForAll(Long id) {

        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            if (Objects.equals(department.getParentId(), id)) {
                department.setParentId(null);
                try {
                    departmentRepository.save(department);
                    LOGGER.info("For department with title {}, deleted parent_id {}", department.getTitle(), id);
                } catch (Exception e) {
                    LOGGER.error("For department with title {}, parent_id is not deleted", department.getTitle());
                }

            }
        }
        return true;

    }


    @Override
    public Department edit(Department department) {
        Department editedDepartment = departmentRepository.getReferenceById(department.getId());

        editedDepartment.setNotes(department.getNotes());
        editedDepartment.setTitle(department.getTitle());
        editedDepartment.setParentId(department.getParentId());

        Department savedDepartment = null;
        try {
            savedDepartment = departmentRepository.save(editedDepartment);
            LOGGER.info("Department saved: {}", savedDepartment);
        } catch (Exception e) {
            LOGGER.error("Department is not saved: {}", savedDepartment);
        }

        return savedDepartment;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department findByTitle(String title) {
        return departmentRepository.findByTitle(title);
    }
}

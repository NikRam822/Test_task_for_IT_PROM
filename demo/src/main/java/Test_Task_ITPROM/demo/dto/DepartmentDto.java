package Test_Task_ITPROM.demo.dto;

import Test_Task_ITPROM.demo.model.Department;
import lombok.Data;

@Data
public class DepartmentDto {

    private String title;

    private String notes;

    private Long parentId;
}

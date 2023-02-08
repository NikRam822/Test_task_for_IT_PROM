package Test_Task_ITPROM.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne()
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    private Profession profession;

    @OneToOne()
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}

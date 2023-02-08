package Test_Task_ITPROM.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "notes")
    private String notes;

    @Column(name = "parent_id")
    private Long parentId;

    public Department(String title, String notes, Long parentId) {
        this.title = title;
        this.notes = notes;
        this.parentId = parentId;
    }
}

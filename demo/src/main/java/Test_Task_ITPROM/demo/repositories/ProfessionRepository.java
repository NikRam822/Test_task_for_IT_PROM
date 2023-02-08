package Test_Task_ITPROM.demo.repositories;

import Test_Task_ITPROM.demo.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    Profession findByTitle(String title);
}

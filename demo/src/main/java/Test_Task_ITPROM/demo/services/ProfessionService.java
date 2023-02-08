package Test_Task_ITPROM.demo.services;

import Test_Task_ITPROM.demo.model.Profession;

import java.util.List;

public interface ProfessionService {
    Profession add(Profession profession);

    boolean delete(Long id);

    Profession edit(Profession profession);

    List<Profession> getAll();

    Profession findByTitle(String title);
}

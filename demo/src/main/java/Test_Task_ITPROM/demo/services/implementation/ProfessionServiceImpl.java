package Test_Task_ITPROM.demo.services.implementation;

import Test_Task_ITPROM.demo.model.Profession;
import Test_Task_ITPROM.demo.repositories.ProfessionRepository;
import Test_Task_ITPROM.demo.services.ProfessionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class ProfessionServiceImpl implements ProfessionService {

    private final Logger LOGGER = log;
    private final ProfessionRepository professionRepository;

    @Autowired
    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    @Override
    public Profession add(Profession profession) {

        Profession savedProfession = null;
        try {
            savedProfession = professionRepository.save(profession);
            LOGGER.info("Profession saved: {}", savedProfession);
        } catch (Exception e) {
            LOGGER.error("Profession is not saved: {}", savedProfession);
        }
        return savedProfession;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            try {
                professionRepository.deleteById(id);
                LOGGER.info("Deleted profession with id: {}", id);
                return true;
            } catch (Exception e) {
                LOGGER.info("Profession with id: {}, is not deleted :{}", id, e);
                return false;
            }

        } else {
            LOGGER.warn("Id is null");
            return false;
        }
    }

    @Override
    public Profession edit(Profession profession) {

        Profession editedProfession = professionRepository.getReferenceById(profession.getId());

        editedProfession.setNotes(profession.getNotes());
        editedProfession.setTitle(profession.getTitle());

        Profession savedProfession = null;
        try {
            savedProfession = professionRepository.save(editedProfession);
            LOGGER.info("Profession saved: {}", savedProfession);
        } catch (Exception e) {
            LOGGER.error("Profession is not saved: {}", savedProfession);
        }

        return savedProfession;
    }

    @Override
    public List<Profession> getAll() {
        return professionRepository.findAll();
    }

    @Override
    public Profession findByTitle(String title) {
        return professionRepository.findByTitle(title);
    }
}

package Test_Task_ITPROM.demo.controllers;

import Test_Task_ITPROM.demo.dto.ProfessionDto;
import Test_Task_ITPROM.demo.model.Profession;
import Test_Task_ITPROM.demo.services.ProfessionService;
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
@RequestMapping("/api/profession")
public class ProfessionController {

    private final Logger LOGGER = log;

    private ProfessionService professionService;

    @Autowired
    public void setProfessionService(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping("/get/all")
    ResponseEntity<List<Profession>> getAll() {
        return new ResponseEntity<>(professionService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<String> addProfession(@RequestBody ProfessionDto professionDto) {
        Profession profession = professionService.findByTitle(professionDto.getTitle());

        if (profession != null) {
            LOGGER.warn("Profession with title {} is already exist", profession.getTitle());
            return new ResponseEntity<>("Profession with this title is already exist", HttpStatus.CONFLICT);
        }
        profession = new Profession();
        profession.setNotes(professionDto.getNotes());
        profession.setTitle(professionDto.getTitle());

        Profession savedProfession = professionService.add(profession);

        if (savedProfession != null) {
            return new ResponseEntity<>("Profession added: " + savedProfession.getTitle(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Profession is not added", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete")
    ResponseEntity<String> delete( Long id) {
        boolean isDeleted = professionService.delete(id);
        if (isDeleted) {
            return new ResponseEntity<>("Profession deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Profession is not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/edit")
    ResponseEntity<String> edit(@RequestBody ProfessionDto professionDto, Long id) {
        Profession profession = professionService.edit(new Profession(id, professionDto.getTitle(), professionDto.getNotes()));

        if (profession != null) {
            return new ResponseEntity<>("Department edited: " + profession.getTitle(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department is not edited: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

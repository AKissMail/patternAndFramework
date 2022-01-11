package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.CategoryEntity;
import de.gruppeo.wise2122_java_server.model.RoundsEntity;
import de.gruppeo.wise2122_java_server.repository.CategoryRepository;
import de.gruppeo.wise2122_java_server.repository.RoundsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rounds")
public class RoundsController {

    private final RoundsRepository roundsRepository;

    public RoundsController(RoundsRepository roundsRepository) {
        this.roundsRepository = roundsRepository;
        List<RoundsEntity> rounds = roundsRepository.findAll();

        Integer[] roundsArray = new Integer[]{10, 15, 20};

/*        for (Integer integer : roundsArray) {
            if (!integer.equals(roundsRepository.findByRounds(integer))) {
                RoundsEntity newRounds = new RoundsEntity();
                newRounds.setRounds(integer);
            }
        }*/

    }

    @GetMapping("")
    public List<RoundsEntity> index() {
        return roundsRepository.findAll();
    }
}

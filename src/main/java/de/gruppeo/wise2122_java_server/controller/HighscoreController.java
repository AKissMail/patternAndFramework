package de.gruppeo.wise2122_java_server.controller;


import de.gruppeo.wise2122_java_server.model.HighscoreEntity;
import de.gruppeo.wise2122_java_server.repository.HighscoreRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Der Highscore controller
 * <p>
 * Verwaltung der maximalen Anzahl an Punkten, die ein Spieler bislang erreicht hat.
 */
@RestController
@RequestMapping("/highscore")
public class HighscoreController {

    private final HighscoreRepository highscoreRepository;

    /**
     * Instantiates a new Highscore controller.
     *
     * @param highscoreRepository the highscore repository
     */
    public HighscoreController(HighscoreRepository highscoreRepository) {
        this.highscoreRepository = highscoreRepository;
    }

    /**
     * Liste / Tabelle der maximalen Punktestände der Spieler
     *
     * @return Liste vom Typ HighscoreEntity
     */
    @GetMapping("")
    public List<HighscoreEntity> index() {
        return highscoreRepository.findAll(Sort.by(Sort.Direction.DESC, "highscorepoints"));
    }
}

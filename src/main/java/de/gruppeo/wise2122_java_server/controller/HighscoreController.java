package de.gruppeo.wise2122_java_server.controller;


import de.gruppeo.wise2122_java_server.entity.Highscore;
import de.gruppeo.wise2122_java_server.repository.HighscoreRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/highscore")
public class HighscoreController {

    private HighscoreRepository highscoreRepository;

    public HighscoreController(HighscoreRepository highscoreRepository) {
        this.highscoreRepository = highscoreRepository;
    }

    @GetMapping("")
    public List<Highscore> index() {
        return highscoreRepository.findAll();
    }
}

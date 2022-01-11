package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.GamesEntity;
import de.gruppeo.wise2122_java_server.model.Gamestatus;
import de.gruppeo.wise2122_java_server.repository.GamesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GamesController {

    private final GamesRepository gamesRepository;

    public GamesController(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;

    }

    // Offene Games
    @GetMapping("/open")
    public List<GamesEntity> findByGamestatus() {
        return gamesRepository.findByGamestatus(Gamestatus.OPEN);
    }

    // Single Game
    @GetMapping("/{id}")
    public Optional<GamesEntity> findById(
            @PathVariable Long id) {
        return gamesRepository.findById(id);
    }

    // Alle Games
    @GetMapping("/all")
    public List<GamesEntity> index() {
        return gamesRepository.findAll();
    }
}

package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.entity.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player/all")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("")
    public List<PlayerEntity> index() {
        return playerRepository.findAll();
    }
}

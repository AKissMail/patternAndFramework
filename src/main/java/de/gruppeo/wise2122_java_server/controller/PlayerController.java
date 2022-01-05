package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/all")
    public List<PlayerEntity> index() {
        return playerRepository.findAll();
    }

    @GetMapping
    public List<PlayerEntity> findByCurrentstatusAllIgnoreCase(
            @RequestParam("status") String status) {

        if (status == null) {
            return playerRepository.findAll();
        } else {
            return playerRepository.findByCurrentstatusAllIgnoreCase(status);
        }
    }
}

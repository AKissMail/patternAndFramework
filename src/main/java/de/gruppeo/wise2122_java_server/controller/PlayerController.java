package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import de.gruppeo.wise2122_java_server.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private final PlayerRepository playerRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public PlayerController(PlayerRepository playerRepository, JwtTokenProvider jwtTokenProvider) {
        this.playerRepository = playerRepository;
        this.jwtTokenProvider = jwtTokenProvider;
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

    @PostMapping("/changeplayerstatus")
    public ResponseEntity<PlayerEntity> changePlayerStatus(@RequestParam Map<String, String> requestParams) throws Exception {
        String status = requestParams.get("status");
        String token = requestParams.get("token");

        if (status == null) {
            return ResponseEntity.badRequest().build();
        } else {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            Optional<PlayerEntity> player = playerRepository.findByUsername(username);

            if (player.isPresent()) {
                PlayerEntity playerToUpdate = player.get();
                playerToUpdate.setCurrentstatus(status);
                PlayerEntity updated = playerRepository.save(playerToUpdate);

                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

    }
}

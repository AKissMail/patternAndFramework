package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.config.CaseInsensitiveEnumEditor;
import de.gruppeo.wise2122_java_server.model.Currentstatus;
import de.gruppeo.wise2122_java_server.model.HighscoreEntity;
import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.HighscoreRepository;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import de.gruppeo.wise2122_java_server.request.HighscoreRequest;
import de.gruppeo.wise2122_java_server.request.StatusRequest;
import de.gruppeo.wise2122_java_server.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private final PlayerRepository playerRepository;
    private final HighscoreRepository highscoreRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public PlayerController(PlayerRepository playerRepository, HighscoreRepository highscoreRepository, JwtTokenProvider jwtTokenProvider) {
        this.playerRepository = playerRepository;
        this.highscoreRepository = highscoreRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/all")
    public List<PlayerEntity> index() {
        return playerRepository.findAll();
    }

    @GetMapping
    public List<PlayerEntity> findByCurrentstatusAllIgnoreCase(
            @RequestParam("status") Currentstatus status) {

        if (status == null) {
            return playerRepository.findAll();
        } else {
            return playerRepository.findByCurrentstatus(status);
        }
    }

    @PostMapping("/changeplayerstatus")
    public ResponseEntity<PlayerEntity> changePlayerStatus(@RequestBody StatusRequest statusRequest) {
        if (statusRequest.getStatus() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            String username = jwtTokenProvider.getUsernameFromToken(statusRequest.getToken());
            Optional<PlayerEntity> player = playerRepository.findByUsername(username);

            if (player.isPresent()) {
                PlayerEntity playerToUpdate = player.get();
                playerToUpdate.setCurrentstatus(Currentstatus.valueOf(statusRequest.getStatus()));
                PlayerEntity updated = playerRepository.save(playerToUpdate);

                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

    }

    @PutMapping("/setPlayerHighscore")
    public ResponseEntity<HighscoreEntity> setPlayerHighscore(@RequestBody HighscoreRequest highscoreRequest) {
        if (highscoreRequest.getPlayerHighscore() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            String username = jwtTokenProvider.getUsernameFromToken(highscoreRequest.getToken());
            Optional<PlayerEntity> player = playerRepository.findByUsername(username);
            if (player.isPresent()) {
                Optional<HighscoreEntity> playerHighscore = highscoreRepository.findByPlayer_Username(username);
                if (playerHighscore.isPresent()) {
                    playerHighscore.get().setHighscorepoints(highscoreRequest.getPlayerHighscore());
                    HighscoreEntity updatePlayerHighscore = highscoreRepository.save(playerHighscore.get());
                    return ResponseEntity.ok(updatePlayerHighscore);
                } else {
                    HighscoreEntity newPlayerHighscore = new HighscoreEntity();
                    newPlayerHighscore.setPlayer(player.get());
                    newPlayerHighscore.setHighscorepoints(highscoreRequest.getPlayerHighscore());
                    highscoreRepository.save(newPlayerHighscore);
                    newPlayerHighscore = highscoreRepository.findByPlayer_Username(username).get();

                    PlayerEntity playerToUpdate = player.get();
                    playerToUpdate.setHighscore(newPlayerHighscore);

                    playerToUpdate = playerRepository.save(playerToUpdate);


                    return ResponseEntity.ok(newPlayerHighscore);
                }
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Currentstatus.class, new CaseInsensitiveEnumEditor(Currentstatus.class));
    }
}

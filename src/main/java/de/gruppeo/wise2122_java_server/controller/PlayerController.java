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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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

    @PutMapping("/setplayerhighscore")
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

                    Optional<HighscoreEntity> newHighscoreEntry = highscoreRepository.findByPlayer_Username(username);
                    if (newHighscoreEntry.isEmpty()) {
                        return ResponseEntity.badRequest().build();
                    }

                    PlayerEntity playerToUpdate = player.get();
                    playerToUpdate.setHighscore(newHighscoreEntry.get());
                    playerRepository.save(playerToUpdate);

                    return ResponseEntity.ok(newPlayerHighscore);
                }
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @PostMapping(value = "/uploadthumbnail",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> uploadThumbnail(
            @RequestParam("playername") String playername,
            @RequestParam("file") MultipartFile file) {
        Optional<PlayerEntity> playerOptional = playerRepository.findByUsername(playername);

        if (playerOptional.isPresent()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            if (fileName.contains("..")) {
                System.out.println("Keine valide Datei!");
            }
            try {
                playerOptional.get().setThumbnail(Base64.getEncoder().encodeToString(file.getBytes()));
                playerRepository.save(playerOptional.get());
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Thumbnail wurde in der Datenbank gespeichert!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Thumbnail konnte nicht gespeichert werden!");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Spieler konnte nicht gefunden werden!");
    }

    @GetMapping(value = "/getthumbnailbyname")
    public ResponseEntity<String> getthumbnailbyid(@RequestParam("playername") String name) {
        Optional<PlayerEntity> playerOptional = playerRepository.findByUsername((name));
        return playerOptional.map(playerEntity ->
                ResponseEntity.ok(playerEntity.getThumbnail())).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Spieler konnte nicht gefunden werden!"));

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Currentstatus.class, new CaseInsensitiveEnumEditor(Currentstatus.class));
    }
}

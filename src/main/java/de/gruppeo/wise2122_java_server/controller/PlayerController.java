package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.config.CaseInsensitiveEnumEditor;
import de.gruppeo.wise2122_java_server.model.Currentstatus;
import de.gruppeo.wise2122_java_server.model.HighscoreEntity;
import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.HighscoreRepository;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import de.gruppeo.wise2122_java_server.request.HighscoreRequest;
import de.gruppeo.wise2122_java_server.request.StatusRequest;
import de.gruppeo.wise2122_java_server.request.UploadImageByStrRequest;
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
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Der Spieler Controller
 * <p>
 * Hier sind die Methoden enthalten, die benötigt werden, um einen Spieler zu verwalten und abzufragen
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final HighscoreRepository highscoreRepository;
    private final JwtTokenProvider jwtTokenProvider;


    /**
     * Instantiates a new Player controller.
     *
     * @param playerRepository    the player repository
     * @param highscoreRepository the highscore repository
     * @param jwtTokenProvider    the jwt token provider
     */
    @Autowired
    public PlayerController(PlayerRepository playerRepository, HighscoreRepository highscoreRepository, JwtTokenProvider jwtTokenProvider) {
        this.playerRepository = playerRepository;
        this.highscoreRepository = highscoreRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Ungefilterte Liste der registrierten Spieler.
     *
     * @return Liste vom Typ PlayerEntity
     */
    @GetMapping("/all")
    public List<PlayerEntity> index() {
        return playerRepository.findAll();
    }

    /**
     * Listet Spieler eines bestimmten Status.
     *
     * @param status Spielerstatus
     * @return Liste vom Typ PlayerEntity
     */
    @GetMapping
    public List<PlayerEntity> findByCurrentstatusAllIgnoreCase(
            @RequestParam("status") Currentstatus status) {

        if (status == null) {
            return playerRepository.findAll();
        } else {
            return playerRepository.findByCurrentstatus(status);
        }
    }

    /**
     * Aktualisiere den Spieler Status
     *
     * @param statusRequest bestehend aus token (Spielername) und zu setzenden status
     * @return response entity vom Typ PlayerEntity
     */
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

    /**
     * Spieler Highscore setzen
     *
     * @param highscoreRequest highscore request bestehend aus token (Spielername) und playerHighscore
     * @return HighscoreEntity des Spielers
     */
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

    /**
     * Upload eines Thumbnails des Spielers
     *
     * @param playername Spielername
     * @param file       Datei
     * @return response entity vom Typ String (Erfolgreich oder Error)
     */
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

    /**
     * Upload eines Spieler Thumbnails, wobei das Bild diesmal als Base64 String übertragen wird
     *
     * @param uploadThumbnailRequest bestehend aus file (Base64 String) und playername (Spielername)
     * @return response entity vom Typ String (erfolgreich oder Fehler)
     */
    @PostMapping(value = "/uploadthumbnailstr")
    public ResponseEntity<String> uploadThumbnail(@RequestBody UploadImageByStrRequest uploadThumbnailRequest) {
        String playername = uploadThumbnailRequest.getPlayername();
        String file = uploadThumbnailRequest.getFile();
        Optional<PlayerEntity> playerOptional = playerRepository.findByUsername(playername);

        if (playerOptional.isPresent() && !file.isEmpty()) {
            playerOptional.get().setThumbnail(file);
            playerRepository.save(playerOptional.get());
            if (playerOptional.get().getThumbnail() != null) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Thumbnail wurde in der Datenbank gespeichert!");
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Thumbnail konnte nicht gespeichert werden!");
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Spieler konnte nicht gefunden werden!");
    }

    /**
     * Liefert Spieler Thumbnail anhand des Spielernamen.
     *
     * @param playername Spielername
     * @return Bild als Base64 String
     */
    @GetMapping(value = "/getthumbnailbyname")
    public ResponseEntity<String> getThumbnailByName(@RequestParam("playername") String playername) {
        Optional<PlayerEntity> playerOptional = playerRepository.findByUsername(playername);

        if (playerOptional.isPresent()) {
            PlayerEntity player = playerOptional.get();
            if (player.getThumbnail().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Spieler hat kein Thumbnail");
            } else {
                return ResponseEntity.ok(player.getThumbnail());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Spieler konnte nicht gefunden werden!");
        }

    }

    /**
     * Init binder für Kleinschreibung bei ENUM Datentypen, hier Currentstatus
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Currentstatus.class, new CaseInsensitiveEnumEditor(Currentstatus.class));
    }
}

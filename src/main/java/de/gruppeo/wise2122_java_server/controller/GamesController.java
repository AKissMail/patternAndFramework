package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.CategoryEntity;
import de.gruppeo.wise2122_java_server.model.GamesEntity;
import de.gruppeo.wise2122_java_server.model.Gamestatus;
import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.CategoryRepository;
import de.gruppeo.wise2122_java_server.repository.GamesRepository;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import de.gruppeo.wise2122_java_server.request.NewGameRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GamesController {

    private final GamesRepository gamesRepository;
    private final PlayerRepository playerRepository;
    private final CategoryRepository categoryRepository;


    public GamesController(GamesRepository gamesRepository, PlayerRepository playerRepository, CategoryRepository categoryRepository) {
        this.gamesRepository = gamesRepository;
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<GamesEntity> createGame(@RequestBody NewGameRequest newGameRequest) {
        Optional<PlayerEntity> playerOne = playerRepository.findByUsername(newGameRequest.getUsername());
        Optional<CategoryEntity> gameCategory = categoryRepository.findByCategoryname(newGameRequest.getCategory());

        if (playerOne.isPresent() && gameCategory.isPresent()) {
            GamesEntity newGame = new GamesEntity();
            newGame.setPlayerone(playerOne.get());
            newGame.setCategory(gameCategory.get());

            GamesEntity createGame = gamesRepository.save(newGame);

            return ResponseEntity.ok(createGame);
        } else {
            return ResponseEntity.badRequest().build();
        }
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

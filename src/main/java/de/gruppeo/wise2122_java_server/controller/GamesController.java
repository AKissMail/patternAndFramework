package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.*;
import de.gruppeo.wise2122_java_server.repository.CategoryRepository;
import de.gruppeo.wise2122_java_server.repository.GamesRepository;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import de.gruppeo.wise2122_java_server.repository.RoundsRepository;
import de.gruppeo.wise2122_java_server.request.DropAnswerRequest;
import de.gruppeo.wise2122_java_server.request.NewGameRequest;
import de.gruppeo.wise2122_java_server.request.UpdateGameRequest;
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
    private final RoundsRepository roundsRepository;


    public GamesController(GamesRepository gamesRepository, PlayerRepository playerRepository, CategoryRepository categoryRepository, RoundsRepository roundsRepository) {
        this.gamesRepository = gamesRepository;
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
        this.roundsRepository = roundsRepository;
    }
    // ein neues Game anlegen
    @PostMapping("/create")
    public ResponseEntity<GamesEntity> createGame(@RequestBody NewGameRequest newGameRequest) {
        Optional<PlayerEntity> playerOne = playerRepository.findByUsername(newGameRequest.getUsername());
        Optional<CategoryEntity> gameCategory = categoryRepository.findByCategoryname(newGameRequest.getCategory());
        Optional<RoundsEntity> gameRound = roundsRepository.findByRounds(newGameRequest.getRounds());

        if (playerOne.isPresent() && gameCategory.isPresent() && gameRound.isPresent()) {
            GamesEntity newGame = new GamesEntity();
            newGame.setGamestatus(Gamestatus.OPEN);
            newGame.setPlayerone(playerOne.get());
            newGame.setPlayeronescore(0);
            newGame.setPlayeroneround(0);
            newGame.setPlayertwoscore(0);
            newGame.setPlayertworound(0);
            newGame.setCategory(gameCategory.get());
            newGame.setRounds(gameRound.get());
            GamesEntity createdGame = gamesRepository.save(newGame);
            return ResponseEntity.ok(createdGame);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Aktualisiere ein Spiel in der Datenbank
     * <p>
     * Mit dieser Methode kann ein Spiel in der Datenbank aktualisiert werden.
     *
     * @param updateGameRequest Man kann folgende Param übergeben: gamesid, playerone, playertwo, status
     * @return GamesEntity
     */
    @PutMapping("/update")
    public ResponseEntity<GamesEntity> updateGame(@RequestBody UpdateGameRequest updateGameRequest) {
        Optional<GamesEntity> updateGame = gamesRepository.findById(updateGameRequest.getGamesid());
        // Prüfe, ob übergebene Gamesid überhaupt existiert
        if (updateGame.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        // Var vom Typ GamesEntity initiieren
        GamesEntity updatedGame;

        // Setze Spielerstatus oder, wenn null, werfe den Spieler aus dem Spiel
        switch (updateGameRequest.getPlayerone() + "-" + updateGameRequest.getPlayertwo()) {
            case "null-":
                updateGame.get().setGamestatus(Gamestatus.valueOf(updateGameRequest.getStatus()));
                updateGame.get().setPlayerone(null);
                updatedGame = gamesRepository.save(updateGame.get());
                return ResponseEntity.ok(updatedGame);
            case "-null":
                updateGame.get().setGamestatus(Gamestatus.valueOf(updateGameRequest.getStatus()));
                updateGame.get().setPlayertwo(null);
                updatedGame = gamesRepository.save(updateGame.get());
                return ResponseEntity.ok(updatedGame);
            case "null-null":
                gamesRepository.delete(updateGame.get());
                if (gamesRepository.existsById(updateGame.get().getId())) {
                    GamesEntity deletedGAme = new GamesEntity();
                    return ResponseEntity.ok(deletedGAme);
                }
                return ResponseEntity.badRequest().build();
            default:
                Optional<PlayerEntity> playerOne = playerRepository.findByUsername(updateGameRequest.getPlayerone());
                Optional<PlayerEntity> playerTwo = playerRepository.findByUsername(updateGameRequest.getPlayertwo());
                if (playerOne.isPresent()) {
                    updateGame.get().setGamestatus(Gamestatus.valueOf(updateGameRequest.getStatus()));
                    updateGame.get().setPlayerone(playerOne.get());

                    updatedGame = gamesRepository.save(updateGame.get());
                    return ResponseEntity.ok(updatedGame);
                } else if (playerTwo.isPresent()) {
                    updateGame.get().setGamestatus(Gamestatus.valueOf(updateGameRequest.getStatus()));
                    updateGame.get().setPlayertwo(playerTwo.get());

                    updatedGame = gamesRepository.save(updateGame.get());
                    return ResponseEntity.ok(updatedGame);
                } else {
                    return ResponseEntity.badRequest().build();
                }
        }
    }
    /* @todo hier ist noch ein Bug bei dem ich nicht weiter komme: Ein Vergleich, der checkt ob beide Spieler Antworten gegeben
          haben hat und den Status aktualisiert...
    // eine Antwort in ein Game eintragen
    @PutMapping("/dropAnswer")
    public ResponseEntity<GamesEntity> dropAnswer(@RequestBody DropAnswerRequest dropAnswerRequest){
        Optional<PlayerEntity> player = playerRepository.findByUsername(dropAnswerRequest.getUsername());
        Optional<GamesEntity> updateGame = gamesRepository.findById(dropAnswerRequest.getGamesid());

        if (player.isPresent() && updateGame.isPresent()) {
         // if (true){ <- wie kann ich hier den player 1 von player 2 unterscheiden? Mir ist nichts eingefallen was auch funktioniert hat.
               if (!dropAnswerRequest.isAnswers()){ // mach die if condition so sinn?
                   updateGame.get().setPlayeronescore(updateGame.get().getPlayeronescore()+ scoreCalculator(dropAnswerRequest.getTime()));
                   updateGame.get().setPlayeroneround(updateGame.get().getPlayeroneround()+1);
                   GamesEntity createGame = gamesRepository.save(updateGame.get());
                   return ResponseEntity.ok(createGame);
               }else{
                   updateGame.get().setPlayeroneround(updateGame.get().getPlayeroneround()+1);
                   GamesEntity createGame = gamesRepository.save(updateGame.get());

                   return ResponseEntity.ok(createGame);
               }
           }
           else{
               if (!dropAnswerRequest.isAnswers()){
                   updateGame.get().setPlayertwoscore(updateGame.get().getPlayeronescore()+scoreCalculator(dropAnswerRequest.getTime()));
                   updateGame.get().setPlayertworound(updateGame.get().getPlayeroneround()+1);
                   GamesEntity createGame = gamesRepository.save(updateGame.get());
                   return ResponseEntity.ok(createGame);
               }else{
                   updateGame.get().setPlayertworound(42);  //(updateGame.get().getPlayeroneround()+1);
                   GamesEntity createGame = gamesRepository.save(updateGame.get());
                   return ResponseEntity.ok(createGame);
               }
           }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

     */

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

    private int scoreCalculator (int time) {
        if (time <= 0) {
            return 0;
        } else {
            return time / 100;
        }
    }
}

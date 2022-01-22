package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.*;
import de.gruppeo.wise2122_java_server.repository.*;
import de.gruppeo.wise2122_java_server.request.DropAnswerRequest;
import de.gruppeo.wise2122_java_server.request.NewGameRequest;
import de.gruppeo.wise2122_java_server.request.UpdateGameRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.gruppeo.wise2122_java_server.model.Gamestatus.CLOSE;
import static de.gruppeo.wise2122_java_server.model.Gamestatus.RUNNING;


/**
 * Das ist der GamesController hier werden alle api schnittstellen rund um ein Spiel behandelt.
 * Hier gibt es:
 * /games/create → ein Spiel erstellen
 * /games/update → ein Spiel updaten (beitreten oder löschen)
 * /games/dropAnswer → eine antwort abgeben
 * /games/history → die Spielhistorie eines Spielers abzurufen
 * /games/{id} → ein bestimmte spiel finden
 * /games/all → all spiele finden
 */
@RestController
@RequestMapping("/games")
public class GamesController {

    private final GamesRepository gamesRepository;
    private final GamesHistoryRepository gamesHistoryRepository;
    private final PlayerRepository playerRepository;
    private final CategoryRepository categoryRepository;
    private final RoundsRepository roundsRepository;
    private final QuestionsRepository questionsRepository;
    private final HighscoreRepository highscoreRepository;

    /**
     * Constructor
     */
    public GamesController(GamesRepository gamesRepository, GamesHistoryRepository gamesHistoryRepository, PlayerRepository playerRepository, CategoryRepository categoryRepository, RoundsRepository roundsRepository, QuestionsRepository questionsRepository, HighscoreRepository highscoreRepository) {
        this.gamesRepository = gamesRepository;
        this.gamesHistoryRepository = gamesHistoryRepository;
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
        this.roundsRepository = roundsRepository;
        this.questionsRepository = questionsRepository;
        this.highscoreRepository = highscoreRepository;
    }

    /**
     * Diese Methode nimmt eine Anfrage für ein neues Spiel an und erstellt und gibt diese zurück.
     * @param newGameRequest Das ist das Request mapping
     * @return das Spiel mit allen Fragen
     */
    @PostMapping("/create")
    public ResponseEntity<GamesEntity> createGame(@RequestBody NewGameRequest newGameRequest) {
        Optional<PlayerEntity> playerOne = playerRepository.findByUsername(newGameRequest.getUsername());
        Optional<CategoryEntity> gameCategory = categoryRepository.findByCategoryname(newGameRequest.getCategory());
        Optional<RoundsEntity> gameRound = roundsRepository.findByRounds(newGameRequest.getRounds());
        List<QuestionsEntity> questionOfCategories = questionsRepository.findByCategory_CategorynameAllIgnoreCase(newGameRequest.getCategory());
        if (playerOne.isPresent() && gameCategory.isPresent() && gameRound.isPresent()) {
            List<QuestionsEntity> randomQuestions = getRandomQuestions(questionOfCategories, newGameRequest.getRounds());
            GamesEntity newGame = new GamesEntity();
            newGame.setGamestatus(Gamestatus.OPEN);
            newGame.setPlayerone(playerOne.get());
            newGame.setPlayeronescore(0);
            newGame.setPlayeroneround(0);
            newGame.setPlayertwoscore(0);
            newGame.setPlayertworound(0);
            newGame.setCategory(gameCategory.get());
            newGame.setQuestions(randomQuestions);
            newGame.setRounds(gameRound.get());
            GamesEntity createdGame = gamesRepository.save(newGame);
            return ResponseEntity.ok(createdGame);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * Mit dieser Methode kann ein Spiel in der Datenbank aktualisiert werden.
     * @param updateGameRequest Man kann folgende Param übergeben: games id, player one, player two, status
     * @return GamesEntity
     */
    @PutMapping("/update")
    public ResponseEntity<GamesEntity> updateGame(@RequestBody UpdateGameRequest updateGameRequest) {
        Optional<GamesEntity> updateGame = gamesRepository.findById(updateGameRequest.getGamesid());
        if (updateGame.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        GamesEntity updatedGame;
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
                if (!gamesRepository.existsById(updateGame.get().getId())) {
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
    /**
     * Nimmt Antworten entgegen und verwaltet das Spiel (Antworten gezählt, Fragen entgegengenommen
     * und Highscore wegschreiben).
     *
     * @param dropAnswerRequest das ist das mapping
     * @return den Aktuellen stand, solange diese auf RUNNING steht.
     */
    @PutMapping("/dropanswer")
    public ResponseEntity<GamesEntity> dropAnswer(@RequestBody DropAnswerRequest dropAnswerRequest) {
        Optional<GamesEntity> updateGame = gamesRepository.findById(dropAnswerRequest.getGamesid());
        if (updateGame.isPresent() && updateGame.get().getGamestatus() == RUNNING) {
            if (dropAnswerRequest.isIsplayerone()) {
                if (dropAnswerRequest.isAnswers()) {
                    updateGame.get().setPlayeronescore(updateGame.get().getPlayeronescore() + scoreCalculator(dropAnswerRequest.getTime()));
                }
                updateGame.get().setPlayeroneround(updateGame.get().getPlayeroneround() + 1);
                if (checkGameCount(updateGame)) {
                    updateGame.get().setGamestatus(CLOSE);
                    createAndSaveGamesHistory(updateGame);
                    int currentScore = updateGame.get().getPlayeronescore();
                    int highscore = highscoreRepository.findByPlayer_Username(updateGame.get().getPlayerone().getUsername()).get().highscorepoints;
                    if (currentScore > highscore) {
                        highscoreRepository.findByPlayer_Username(updateGame.get().getPlayertwo().getUsername()).get().setHighscorepoints(updateGame.get().getPlayertwoscore());
                        highscoreRepository.findByPlayer_Username(updateGame.get().getPlayerone().getUsername()).get().setHighscorepoints(currentScore);
                    }
                }
                GamesEntity createGame = gamesRepository.save(updateGame.get());
                return ResponseEntity.ok(createGame);
            } else {
                if (dropAnswerRequest.isAnswers()) {
                    updateGame.get().setPlayertwoscore(updateGame.get().getPlayertwoscore() + scoreCalculator(dropAnswerRequest.getTime()));
                }
                updateGame.get().setPlayertworound(updateGame.get().getPlayertworound() + 1);
                if (checkGameCount(updateGame)) {
                    updateGame.get().setGamestatus(CLOSE);
                    createAndSaveGamesHistory(updateGame);
                    int currentScore = updateGame.get().getPlayertwoscore();
                    int highscore = highscoreRepository.findByPlayer_Username(updateGame.get().getPlayertwo().getUsername()).get().highscorepoints;
                    if (currentScore > highscore) {
                        highscoreRepository.findByPlayer_Username(updateGame.get().getPlayertwo().getUsername()).get().setHighscorepoints(currentScore);
                        highscoreRepository.findByPlayer_Username(updateGame.get().getPlayerone().getUsername()).get().setHighscorepoints(updateGame.get().getPlayertwoscore());
                    }
                }
                GamesEntity createGame = gamesRepository.save(updateGame.get());
                return ResponseEntity.ok(createGame);
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Die Methode gibt eine Liste offener Spiele zurück
     * @return Liste offener
     */
    @GetMapping("/open")
    public List<GamesEntity> findByGamestatus() {
        return gamesRepository.findByGamestatus(Gamestatus.OPEN);
    }
    /**
     * Diese Methode gibt ein bestimmtes Spiel zurück anhand einer ID
     * @param id die ID des zu suchen
     * @return das gesuchte Spiel
     */

    @GetMapping("/{id}")
    public Optional<GamesEntity> findById(@PathVariable Long id) {
        return gamesRepository.findById(id);
    }
    /**
     * Mit dieser Methode werden alle Spiele gefunden und zurück übermittelt
     * @return die Spiele
     */

    @GetMapping("/all")
    public List<GamesEntity> index() {
        return gamesRepository.findAll();
    }
    /**
     * Dieser Methode werden die Punkte zurückgegeben
     * @param time Zeit die der Spieler gebraucht hat
     * @return Die Punkte
     */
    private int scoreCalculator(int time) {
        if (time <= 100) {
            return 1;
        } else {
            return time / 100;
        }
    }

    /**
     * Diese Methode schreibt Spiele, welche beendet wurden in die games_history Tabelle.
     *
     * @param updateGame das Spiel, welches beendet wurde
     */
    private void createAndSaveGamesHistory(Optional<GamesEntity> updateGame) {
        if (updateGame.isPresent()) {
            // History für Spieler 1
            GamesHistoryEntity newGameHistoryEntryOne = new GamesHistoryEntity();
            newGameHistoryEntryOne.setRounds(updateGame.get().getRounds());
            newGameHistoryEntryOne.setPlayername(updateGame.get().getPlayerone());
            newGameHistoryEntryOne.setCategory(updateGame.get().getCategory());
            newGameHistoryEntryOne.setPlayerscore(updateGame.get().getPlayeronescore());
            newGameHistoryEntryOne.setOpponentscore(updateGame.get().getPlayertwoscore());
            gamesHistoryRepository.save(newGameHistoryEntryOne);
            // History für Spieler 2
            GamesHistoryEntity newGameHistoryEntryTwo = new GamesHistoryEntity();
            newGameHistoryEntryTwo.setRounds(updateGame.get().getRounds());
            newGameHistoryEntryTwo.setPlayername(updateGame.get().getPlayertwo());
            newGameHistoryEntryTwo.setCategory(updateGame.get().getCategory());
            newGameHistoryEntryTwo.setPlayerscore(updateGame.get().getPlayertwoscore());
            newGameHistoryEntryTwo.setOpponentscore(updateGame.get().getPlayeronescore());
            gamesHistoryRepository.save(newGameHistoryEntryTwo);
        }
    }

    /**
     * Das ist der Get um die Spielhistorie eines Spielers abzurufen
     *
     * @param playername der Spielername
     * @return die liste der beendeten Spiele
     */
    @GetMapping("/history")
    public List<GamesHistoryEntity> showGamesHistory(@RequestParam String playername) {
        if (playername == null || playername.isEmpty()) {
            return gamesHistoryRepository.findAll();
        } else {
            return gamesHistoryRepository.findByPlayername_Username(playername);
        }
    }

    /**
     * Diese Methode löscht die History-Eintröge eines Spielers.
     *
     * @param playername der Spielername
     * @return die liste der beendeten Spiele
     */
    @PutMapping("/historydelete")
    public ResponseEntity<String> deleteGamesHistory(@RequestParam("playername") String playername) {
        List<GamesHistoryEntity> gamesHistory = gamesHistoryRepository.findByPlayername_Username(playername);

        if (gamesHistory.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("History des Spielers " + playername + " konnte nicht gefunden werden!");
        } else {
            gamesHistory.forEach(gamesHistoryRepository::delete);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("History des Spielers " + playername + " wurde gelöscht");
        }
    }

    /**
     * Diese Methode überprüft, ob das Spiel zu Ende ist.
     *
     * @param gamesRepository das zu prüfende Spiel
     * @return true - Spiel ist zu Ende | false - Spiel noch nicht zu Ende
     */
    public boolean checkGameCount(Optional<GamesEntity> gamesRepository) {
        if (gamesRepository.isPresent()) {
            int playerOne = gamesRepository.get().getPlayeroneround();
            int playerTwo = gamesRepository.get().getPlayertworound();
            int gameSize = gamesRepository.get().getRounds().getRounds();
            return playerTwo == gameSize && playerOne == gameSize;
        }
        return false;
    }

    /**
     * Wählt aus ein Set von Fragen, eine bestimme anzahl an Fragen zufällig aus.
     * @param questions         das Set an Fragen
     * @param numberOfQuestions die anzahl der Fragen die ausgewählt wurden´
     * @return Die Liste der ausgewählten Fragen
     */
    public List<QuestionsEntity> getRandomQuestions(List<QuestionsEntity> questions, int numberOfQuestions) {
        List<QuestionsEntity> randomQuestions = new ArrayList<>();
        List<QuestionsEntity> copy = new ArrayList<>(questions);

        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < Math.min(numberOfQuestions, questions.size()); i++) {
            randomQuestions.add(copy.remove(rand.nextInt(copy.size())));
        }
        return randomQuestions;
    }
}

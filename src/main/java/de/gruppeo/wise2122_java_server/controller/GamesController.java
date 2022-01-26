package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.*;
import de.gruppeo.wise2122_java_server.repository.*;
import de.gruppeo.wise2122_java_server.request.DeletGameHistoryRequestWithJSON;
import de.gruppeo.wise2122_java_server.request.DropAnswerRequest;
import de.gruppeo.wise2122_java_server.request.NewGameRequest;
import de.gruppeo.wise2122_java_server.request.UpdateGameRequest;
import de.gruppeo.wise2122_java_server.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.gruppeo.wise2122_java_server.model.Gamestatus.*;


/**
 * Die GamesController-Klasse erstellt die wichtigsten REST-Schnittstellen zur Erstellung und Betrieb der Quiz-Runden.
 * Es werden alle Api-schnittstellen rund um ein Spiel behandelt:
 * <p>
 * /games/create        ein Spiel erstellen
 * /games/update        ein Spiel aktualisieren (beitreten oder löschen)
 * /games/dropAnswer    eine Antwort abgeben
 * /games/history       die Spielhistorie eines Spielers abrufen
 * /games/{id}          ein bestimmtes Spiel finden
 * /games/all           alle Spiele auflisten
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
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Constructor
     *  @param gamesRepository        games repository
     * @param gamesHistoryRepository games history repository
     * @param playerRepository       player repository
     * @param categoryRepository     category repository
     * @param roundsRepository       rounds repository
     * @param questionsRepository    questions repository
     * @param highscoreRepository    highscore repository
     * @param jwtTokenProvider
     */
    public GamesController(GamesRepository gamesRepository, GamesHistoryRepository gamesHistoryRepository,
                           PlayerRepository playerRepository, CategoryRepository categoryRepository,
                           RoundsRepository roundsRepository, QuestionsRepository questionsRepository,
                           HighscoreRepository highscoreRepository, JwtTokenProvider jwtTokenProvider) {
        this.gamesRepository = gamesRepository;
        this.gamesHistoryRepository = gamesHistoryRepository;
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
        this.roundsRepository = roundsRepository;
        this.questionsRepository = questionsRepository;
        this.highscoreRepository = highscoreRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Diese Methode nimmt eine Anfrage für ein neues Spiel an. Anschließend wird das Spiel erstellt
     * und die Metadaten zurückgegeben
     *
     * @param newGameRequest Besteht aus username (playername), category (Spielkategorie), rounds (Anzahl Quizrunden)
     * @return das erstellte Spiel (GamesEntity) mit den zufällig gewürfelten Fragen
     */
    @PostMapping("/create")
    public ResponseEntity<GamesEntity> createGame(@RequestBody NewGameRequest newGameRequest) {
        Optional<PlayerEntity> playerOne = playerRepository.findByUsername(newGameRequest.getUsername());
        Optional<CategoryEntity> gameCategory = categoryRepository.findByCategoryname(newGameRequest.getCategory());
        Optional<RoundsEntity> gameRound = roundsRepository.findByRounds(newGameRequest.getRounds());

        // Liste der laufenden und offenen Spiele
        List<GamesEntity> runningGames = gamesRepository.findByGamestatus(RUNNING);
        List<GamesEntity> openGames = gamesRepository.findByGamestatus(OPEN);
        // Werden welche gefunden, wird geprüft, ob es sich um Leichen handelt
        if (!runningGames.isEmpty()) {
            runningGames.forEach(runningGame -> {
                // Abstand zwischen den Aktualisierungen der Spieler für jedes offene Spiel ermitteln
                long durationPlayerUpdates = ChronoUnit.SECONDS.between(runningGame.getPlayerOneLastRequestTime(),
                        runningGame.getPlayerTwoLastRequestTime());
                long durationCreatedAndNow = ChronoUnit.SECONDS.between(runningGame.getCreated(), LocalDateTime.now());
                System.out.println(">>> GameID: >" + runningGame.getId() + "< ::: Der Abstand zwischen den " +
                        "Spielaktualisierungen der beiden Spieler liegt bei >" + durationPlayerUpdates + "< Sekunden");
                System.out.println(">>> GameID: >" + runningGame.getId() + "< ::: Der Abstand zwischen dem " +
                        "Erstellungsdatum >" + runningGame.getCreated() + "< und Jetzt liegt bei >" + durationCreatedAndNow + "< Sekunden");
                if ((durationPlayerUpdates > runningGame.getRounds().rounds * 10) ||
                        durationCreatedAndNow > runningGame.getRounds().rounds * 11L) {
                    runningGame.setGamestatus(Gamestatus.CLOSE);
                    System.out.println(">>> GameID: >" + runningGame.getId() + "< ::: Spiel wurde auf Status >CLOSE< gesetzt!");
                    gamesRepository.save(runningGame);
                }
            });
        }
        if (!openGames.isEmpty()) {
            openGames.forEach(openGame -> {
                // Abstand zwischen den Jetzt und dem Erstellungsdatum zu groß
                long durationCreatedAndNow = ChronoUnit.SECONDS.between(openGame.getCreated(), LocalDateTime.now());
                System.out.println(">>> GameID: >" + openGame.getId() + "< ::: Der Abstand zwischen dem " +
                        "Erstellungsdatum >" + openGame.getCreated() + "< und Jetzt liegt bei >" + durationCreatedAndNow + "< Sekunden");
                if (durationCreatedAndNow > openGame.getRounds().rounds * 11L) {
                    openGame.setGamestatus(Gamestatus.CLOSE);
                    System.out.println(">>> GameID: >" + openGame.getId() + "< ::: Spiel wurde auf Status >CLOSE< gesetzt!");
                    gamesRepository.save(openGame);
                }
            });
        }

        List<QuestionsEntity> questionOfCategories = questionsRepository.findByCategory_CategorynameAllIgnoreCase(newGameRequest.getCategory());

        // Prüfen, ob die übergebenen Parameter zu Findungen in der DB geführt haben
        if (playerOne.isPresent() && gameCategory.isPresent() && gameRound.isPresent() && !questionOfCategories.isEmpty()) {
            List<QuestionsEntity> randomQuestions = getRandomQuestions(questionOfCategories, newGameRequest.getRounds());

            // Spielobjekt erstellen
            GamesEntity newGame = new GamesEntity();
            newGame.setGamestatus(OPEN);
            newGame.setPlayerone(playerOne.get());
            newGame.setPlayeronescore(0);
            newGame.setPlayeroneround(0);
            newGame.setPlayerOneLastRequestTime(LocalDateTime.now());
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
     * Mit dieser Methode wird ein Spiel in der Datenbank aktualisiert.
     *
     * @param updateGameRequest Man kann folgende Parameter übergeben: gamesid, playerone, playertwo, status
     * @return GamesEntity Spiel mit den aktualisierten Daten
     */
    @PutMapping("/update")
    public ResponseEntity<GamesEntity> updateGame(@RequestBody UpdateGameRequest updateGameRequest) {
        Optional<GamesEntity> updateGame = gamesRepository.findById(updateGameRequest.getGamesid());
        if (updateGame.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        GamesEntity updatedGame;

        if (updateGame.get().getPlayerOneLastRequestTime() != null &&
                updateGame.get().getPlayerTwoLastRequestTime() != null) {
            long durationPlayerUpdates = ChronoUnit.SECONDS.between(updateGame.get().getPlayerOneLastRequestTime(),
                    updateGame.get().getPlayerTwoLastRequestTime());
            System.out.println(">>> Der Abstand zwischen den Spielaktualisierungen der beiden Spieler liegt bei" +
                    " >" + durationPlayerUpdates + "< Sekunden");
        }

        // Hier wird unterschieden, um was für eine Art Aktualisierung es sich handelt
        // und wer diese veranlasst hat
        switch (updateGameRequest.getPlayerone() + "-" + updateGameRequest.getPlayertwo()) {
            case "null-":
                updateGame.get().setGamestatus(Gamestatus.valueOf(updateGameRequest.getStatus()));
                updateGame.get().setPlayerone(null);
                updatedGame = gamesRepository.save(updateGame.get());
                updateGame.get().setPlayerOneLastRequestTime(LocalDateTime.now());
                return ResponseEntity.ok(updatedGame);
            case "-null":
                updateGame.get().setGamestatus(Gamestatus.valueOf(updateGameRequest.getStatus()));
                updateGame.get().setPlayertwo(null);
                updateGame.get().setPlayerTwoLastRequestTime(LocalDateTime.now());
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
                    //gamesRepository.updatePlayerOneRequestTime(LocalDateTime.now(),updateGame.get().getId());
                    updateGame.get().setPlayerOneLastRequestTime(LocalDateTime.now());

                    updatedGame = gamesRepository.save(updateGame.get());
                    return ResponseEntity.ok(updatedGame);
                } else if (playerTwo.isPresent()) {
                    updateGame.get().setGamestatus(Gamestatus.valueOf(updateGameRequest.getStatus()));
                    updateGame.get().setPlayertwo(playerTwo.get());
                    //gamesRepository.updatePlayerTwoRequestTime(LocalDateTime.now(),updateGame.get().getId());
                    updateGame.get().setPlayerTwoLastRequestTime(LocalDateTime.now());

                    updatedGame = gamesRepository.save(updateGame.get());
                    return ResponseEntity.ok(updatedGame);
                } else {
                    return ResponseEntity.badRequest().build();
                }
        }
    }

    /**
     * Nimmt Antworten entgegen und verwaltet die laufenden Spielrunden
     * - Antworten zählen
     * - Antwort richtig oder falsch
     * - am Ende weird der Highscore weggeschrieben
     *
     * @param dropAnswerRequest bestehend aus gamesid, isplayerone (true oder false), answers,
     *                          time (Zeitpunkt, bei dem die Frage beantwortet worden ist)
     * @return GamesEntity das aktualisierte Spiel, solange es im Status RUNNING ist
     */
    @PutMapping("/dropanswer")
    public ResponseEntity<GamesEntity> dropAnswer(@RequestBody DropAnswerRequest dropAnswerRequest) {
        Optional<GamesEntity> updateGame = gamesRepository.findById(dropAnswerRequest.getGamesid());
        if (updateGame.isPresent() && updateGame.get().getGamestatus() == RUNNING) {

            if (updateGame.get().getPlayerOneLastRequestTime() != null &&
                    updateGame.get().getPlayerTwoLastRequestTime() != null) {
                long durationPlayerUpdates = ChronoUnit.SECONDS.between(updateGame.get().getPlayerOneLastRequestTime(),
                        updateGame.get().getPlayerTwoLastRequestTime());
                System.out.println(">>> Der Abstand zwischen den Spielaktualisierungen der beiden Spieler liegt bei" +
                        " >" + durationPlayerUpdates + "< Sekunden");
            }

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
                //gamesRepository.updatePlayerOneRequestTime(LocalDateTime.now(),updateGame.get().getId());
                updateGame.get().setPlayerOneLastRequestTime(LocalDateTime.now());
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
                //gamesRepository.updatePlayerOneRequestTime(LocalDateTime.now(),updateGame.get().getId());
                updateGame.get().setPlayerTwoLastRequestTime(LocalDateTime.now());
                GamesEntity createGame = gamesRepository.save(updateGame.get());
                return ResponseEntity.ok(createGame);
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Die Methode gibt eine Liste offener Spiele zurück
     *
     * @return GamesEntity Liste offener Spiele
     */
    @GetMapping("/open")
    public List<GamesEntity> findByGamestatus() {
        return gamesRepository.findByGamestatus(OPEN);
    }

    /**
     * Diese Methode gibt ein Spiel anhand der gamesid zurück
     *
     * @param id die gamesid des zu suchenden Spiels
     * @return GamesEntity das gesuchte Spiel
     */
    @GetMapping("/{id}")
    public Optional<GamesEntity> findById(@PathVariable Long id) {
        return gamesRepository.findById(id);
    }

    /**
     * Mit dieser Methode werden alle Spiele als Liste angezeigt
     *
     * @return GamesEntity Liste der Spiele
     */
    @GetMapping("/all")
    public List<GamesEntity> index() {
        return gamesRepository.findAll();
    }

    /**
     * Dieser Methode werden die Punkte anhand der Zeit berechnet
     *
     * @param time Zeit die der Spieler gebraucht hat
     * @return Punkte (score) als int
     */
    private int scoreCalculator(int time) {
        if (time <= 100) {
            return 1;
        } else {
            return time / 100;
        }
    }

    /**
     * Diese Methode schreibt beendete Spiele in die games_history Tabelle.
     *
     * @param updateGame das Spiel (GamesEntity), welches beendet wurde
     */
    private void createAndSaveGamesHistory(Optional<GamesEntity> updateGame) {
        if (updateGame.isPresent()) {
            // History für Spieler 1
            GamesHistoryEntity newGameHistoryEntryOne = new GamesHistoryEntity();
            newGameHistoryEntryOne.setRounds(updateGame.get().getRounds().getRounds());
            newGameHistoryEntryOne.setPlayername(updateGame.get().getPlayerone().getUsername());
            newGameHistoryEntryOne.setPlayer(updateGame.get().getPlayerone());
            newGameHistoryEntryOne.setCategoryname(updateGame.get().getCategory().getCategoryname());
            newGameHistoryEntryOne.setPlayerscore(updateGame.get().getPlayeronescore());
            newGameHistoryEntryOne.setOpponentscore(updateGame.get().getPlayertwoscore());
            gamesHistoryRepository.save(newGameHistoryEntryOne);
            // History für Spieler 2
            GamesHistoryEntity newGameHistoryEntryTwo = new GamesHistoryEntity();
            newGameHistoryEntryTwo.setRounds(updateGame.get().getRounds().getRounds());
            newGameHistoryEntryTwo.setPlayername(updateGame.get().getPlayertwo().getUsername());
            newGameHistoryEntryTwo.setPlayer(updateGame.get().getPlayertwo());
            newGameHistoryEntryTwo.setCategoryname(updateGame.get().getCategory().getCategoryname());
            newGameHistoryEntryTwo.setPlayerscore(updateGame.get().getPlayertwoscore());
            newGameHistoryEntryTwo.setOpponentscore(updateGame.get().getPlayeronescore());
            gamesHistoryRepository.save(newGameHistoryEntryTwo);
        }
    }

    /**
     * Das ist der Get um die Spielhistorie eines Spielers abzurufen
     *
     * @param playername der Spielername
     * @return die liste der beendeten Spiele (GamesHistoryEntity)
     */
    @GetMapping("/history")
    public List<GamesHistoryEntity> showGamesHistory(@RequestParam String playername) {
        if (playername == null || playername.isEmpty()) {
            return gamesHistoryRepository.findAll();
        } else {
            return gamesHistoryRepository.findByPlayer_Username(playername);
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
        List<GamesHistoryEntity> gamesHistory = gamesHistoryRepository.findByPlayer_Username(playername);

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

    @PostMapping("/historydeletebytoken")
    public ResponseEntity<String> deleteGamesHistoryByToken(@RequestBody DeletGameHistoryRequestWithJSON deletGameHistoryRequestWithJSON) {
        String username = jwtTokenProvider.getUsernameFromToken(deletGameHistoryRequestWithJSON.getToken());
        List<GamesHistoryEntity> gamesHistory = gamesHistoryRepository.findByPlayer_Username(username);

        if (gamesHistory.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("History des Spielers " + username + " konnte nicht gefunden werden!");
        } else {
            gamesHistory.forEach(gamesHistoryRepository::delete);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("History des Spielers " + username + " wurde gelöscht");
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
     * Wählt aus den Fragen einer Kategorie, eine bestimme Anzahl an Fragen zufällig (würfeln) aus.
     *
     * @param questions         das Set an Fragen
     * @param numberOfQuestions die Anzahl der zu würfelnden Fragen
     * @return Liste der ausgewählten Fragen (QuestionsEntity)
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

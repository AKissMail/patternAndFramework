package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.RoundsEntity;
import de.gruppeo.wise2122_java_server.repository.RoundsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Der Runden Controller gibt die mögliche Rundenanzahl in den Clients vor
 */
@RestController
@RequestMapping("/rounds")
public class RoundsController {

    private final RoundsRepository roundsRepository;

    /**
     * Instanziiert den Runden-Controller.
     *
     * @param roundsRepository Runden repository
     */
    public RoundsController(RoundsRepository roundsRepository) {
        this.roundsRepository = roundsRepository;
    }

    /**
     * Gibt die Anzahl Runden als Ganzzahliger Wert aus
     *
     * @return Liste vom Typ RoundsEntity
     */
    @GetMapping("")
    public List<RoundsEntity> index() {
        return roundsRepository.findAll();
    }
}

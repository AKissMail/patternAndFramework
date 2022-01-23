package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.HighscoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface für Abfrage der Tabelle highscore
 */
@Repository
public interface HighscoreRepository extends JpaRepository<HighscoreEntity, Long> {
    /**
     * Finde den Highscore zu einem Nutzernamen.
     *
     * @param Username der name
     * @return der gefundene Score vom Typ HighscoreEntity
     */
    Optional<HighscoreEntity> findByPlayer_Username(String Username);
}
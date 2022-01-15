package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.HighscoreEntity;
import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface für die Abfrage der Tabelle highscore
 */
@Repository
public interface HighscoreRepository extends JpaRepository<HighscoreEntity, Long> {
    /**
     * Finde den Highscore zu einem Nutzernamen.
     * @param Username der name
     * @return der gefundene Score.
     */
    Optional<HighscoreEntity> findByPlayer_Username(String Username);
}
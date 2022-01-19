package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.GamesEntity;
import de.gruppeo.wise2122_java_server.model.Gamestatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface für die Abfrage der Tabelle games
 */
@Repository
public interface GamesRepository extends JpaRepository<GamesEntity, Long> {
    /**
     * Sucht Spiel nach Status
      * @param gamestatus der Status
     * @return Die gefundenen Spiele.
     */
    List<GamesEntity> findByGamestatus(Gamestatus gamestatus);
}

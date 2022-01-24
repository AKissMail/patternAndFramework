package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.RoundsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface für die Abfrage der Tabelle rounds
 */
@Repository
public interface RoundsRepository extends JpaRepository<RoundsEntity, Long> {
    /**
     * gibt die gesuchte Runde zurück
     *
     * @param rounds als int
     * @return die runde vom Typ RoundsEntity
     */
    Optional<RoundsEntity> findByRounds(Integer rounds);
}
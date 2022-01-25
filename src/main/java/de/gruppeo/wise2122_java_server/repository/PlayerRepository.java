package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.Currentstatus;
import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Interface für die Abfrage der Tabelle player
 */
@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    /**
     * Hier wird ein Spieler nach Name gesucht
     *
     * @param username der name des gesuchten Spielers
     * @return Spieler vom Typ PlayerEntity
     */
    Optional<PlayerEntity> findByUsername(String username);

    /**
     * Hier werden Spiele nach ihren Status gesucht
     *
     * @param currentstatus Status
     * @return die Spieler als Liste
     */
    List<PlayerEntity> findByCurrentstatus(Currentstatus currentstatus);

}
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
     * Hier wird ein User nach name gesucht
     *
     * @param Username der name des gesuchten
     * @return den Spieler vom Typ PlayerEntity
     */
    Optional<PlayerEntity> findByUsername(String Username);

    /**
     * Hier werden Spiel nach ihren Staus gesucht
     * @param currentstatus Status
     * @return die Spieler als Liste
     */
    List<PlayerEntity> findByCurrentstatus(Currentstatus currentstatus);
}
package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.GamesEntity;
import de.gruppeo.wise2122_java_server.model.Gamestatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface für die Abfrage der Tabelle games
 */
@Repository
public interface GamesRepository extends JpaRepository<GamesEntity, Long> {
    /**
     * Sucht Spiel nach Status
     *
     * @param gamestatus der Status
     * @return Die gefundenen Spiele vom Typ GamesEntity
     */
    List<GamesEntity> findByGamestatus(Gamestatus gamestatus);

    @Modifying
    @Query("UPDATE games g SET g.playerOneLastRequestTime=:datetime WHERE g.id=:id")
    void updatePlayerOneRequestTime(@Param("datetime") LocalDateTime dateTime, @Param("id") long gameid);

    @Modifying
    @Query("update games g set g.playerTwoLastRequestTime=:datetime WHERE g.id=:id")
    void updatePlayerTwoRequestTime(@Param("datetime") LocalDateTime dateTime, @Param("id") long gameid);

}

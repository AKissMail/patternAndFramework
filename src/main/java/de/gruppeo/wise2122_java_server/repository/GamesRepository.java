package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.GamesEntity;
import de.gruppeo.wise2122_java_server.model.Gamestatus;
import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<GamesEntity, Long> {

    List<GamesEntity> findByGamestatus(Gamestatus gamestatus);

    List<GamesEntity> findByPlayerone(PlayerEntity player);
}
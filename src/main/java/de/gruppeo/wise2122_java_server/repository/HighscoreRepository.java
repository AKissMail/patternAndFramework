package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.HighscoreEntity;
import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HighscoreRepository extends JpaRepository<HighscoreEntity, Long> {
    Optional<HighscoreEntity> findByPlayer_Username(String Username);

}
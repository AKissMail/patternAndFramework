package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.HighscoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighscoreRepository extends JpaRepository<HighscoreEntity, Long> {
}
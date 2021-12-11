package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.entity.HighscoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighscoreRepository extends JpaRepository<HighscoreEntity, Long> {
}
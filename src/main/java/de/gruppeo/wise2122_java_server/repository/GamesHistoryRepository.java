package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.GamesHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesHistoryRepository extends JpaRepository<GamesHistoryEntity, Long> {

    List<GamesHistoryEntity> findByPlayername_Username(String username);

}
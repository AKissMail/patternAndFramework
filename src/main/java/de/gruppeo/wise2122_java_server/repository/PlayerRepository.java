package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    Optional<PlayerEntity> findByUsername(String Username);

    List<PlayerEntity> findByCurrentstatusAllIgnoreCase(String CurrentStatus);

    PlayerEntity findByPlayerid(Long PlayerId);

}
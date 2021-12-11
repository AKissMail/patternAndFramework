package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}
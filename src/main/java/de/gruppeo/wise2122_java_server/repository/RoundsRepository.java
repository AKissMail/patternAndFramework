package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.CategoryEntity;
import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.model.RoundsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoundsRepository extends JpaRepository<RoundsEntity, Long> {
    Optional<RoundsEntity> findByRounds(Integer rounds);
}
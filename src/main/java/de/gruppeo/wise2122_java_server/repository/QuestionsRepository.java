package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.entity.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {
}
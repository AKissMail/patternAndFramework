package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.QuestionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {
    List<QuestionsEntity> findByCategory_CategorynameAllIgnoreCase(String Category);
}
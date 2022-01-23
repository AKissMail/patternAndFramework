package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Interface für die Abfrage der Tabelle questions
 */
@Repository
public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {
    /**
     * Hier werden die Fragen anhand einer Kategorie gesucht und zurückgegeben.
     *
     * @param Category die Kategorie
     * @return alle gefunden Fragen als Liste
     */
    List<QuestionsEntity> findByCategory_CategorynameAllIgnoreCase(String Category);
}
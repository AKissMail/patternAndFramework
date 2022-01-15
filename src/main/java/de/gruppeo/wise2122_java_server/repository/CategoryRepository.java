package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Interface für die Abfrage der Tabelle category
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    /**
     * Sucht die Kategorie nach eine gegeben String
     * @param categoryname Suchbegriff
     * @return das was es an Kategorie findet
     */
    Optional<CategoryEntity> findByCategoryname(String categoryname);

}
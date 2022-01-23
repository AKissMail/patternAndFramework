package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Interface für die Abfrage der Tabelle category
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    /**
     * Sucht die Kategorie anhand des übergebenen Parameters Kategoriename
     *
     * @param categoryname Suchbegriff
     * @return Eintrag vom Typ CategoryEntity
     */
    Optional<CategoryEntity> findByCategoryname(String categoryname);

}
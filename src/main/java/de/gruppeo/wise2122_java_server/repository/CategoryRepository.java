package de.gruppeo.wise2122_java_server.repository;

import de.gruppeo.wise2122_java_server.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
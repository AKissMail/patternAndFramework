package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.CategoryEntity;
import de.gruppeo.wise2122_java_server.model.QuestionsEntity;
import de.gruppeo.wise2122_java_server.repository.CategoryRepository;
import de.gruppeo.wise2122_java_server.repository.QuestionsRepository;
import de.gruppeo.wise2122_java_server.repository.RoundsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST-Schnittstelle zur Abfrage der verfügbaren Kategorien im Quiz-Spiel
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final QuestionsRepository questionRepository;
    private final RoundsRepository roundsRepository;

    /**
     * Instanziiert einen neuen Category controller.
     *
     * @param categoryRepository category repository
     * @param questionRepository question repository
     * @param roundsRepository   Runden repository
     */
    public CategoryController(CategoryRepository categoryRepository, QuestionsRepository questionRepository, RoundsRepository roundsRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.roundsRepository = roundsRepository;
    }

    /**
     * Diese Methode stellt nur Kategorien bereit, für die auch Fragen existieren
     *
     * @return Liste der gefilterten Kategorien (CategoryEntity)
     */
    @GetMapping("")
    public List<CategoryEntity> index() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryEntity> filteredCategories = new ArrayList<>();
        int maxRounds = roundsRepository.getMaxRounds();

        categories.forEach(category -> {
            List<QuestionsEntity> questionOfCategories = questionRepository.findByCategory_CategorynameAllIgnoreCase(category.getCategoryname());
            if (!questionOfCategories.isEmpty() && questionOfCategories.size() > maxRounds) {
                filteredCategories.add(category);
            }
        });

        return filteredCategories;
    }
}

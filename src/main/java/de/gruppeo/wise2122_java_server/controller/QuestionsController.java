package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.QuestionsEntity;
import de.gruppeo.wise2122_java_server.repository.QuestionsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Der Fragen Controller.
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsRepository questionsRepository;

    /**
     * Instantiates a new Questions controller.
     *
     * @param questionsRepository the questions repository
     */
    public QuestionsController(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;

    }

    /**
     * Liste aller Fragen
     *
     * @return Liste vom Typ QuestionsEntity
     */
    @GetMapping("/all")
    public List<QuestionsEntity> index() {
        return questionsRepository.findAll();
    }

    /**
     * Liste, welche nur Fragen einer als Parameter übergebenen Kategorie ausgibt
     *
     * @param category Kategorie
     * @return Liste vom Typ QuestionsEntity
     */
    @GetMapping
    public List<QuestionsEntity> findByCategory_CategorynameAllIgnoreCase(
            @RequestParam("category") String category) {

        if (category == null) {
            return questionsRepository.findAll();
        } else {
            return questionsRepository.findByCategory_CategorynameAllIgnoreCase(category);
        }
    }

}

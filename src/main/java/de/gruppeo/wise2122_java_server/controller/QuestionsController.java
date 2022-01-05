package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.QuestionsEntity;
import de.gruppeo.wise2122_java_server.repository.QuestionsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsRepository questionsRepository;

    public QuestionsController(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;

    }

    @GetMapping("/all")
    public List<QuestionsEntity> index() {
        return questionsRepository.findAll();
    }

    @GetMapping
    public List<QuestionsEntity> findByCategory_Categoryname(
            @RequestParam("category") String category) {

        if (category == null) {
            return questionsRepository.findAll();
        } else {
            return questionsRepository.findByCategory_CategorynameAllIgnoreCase(category);
        }
    }

}

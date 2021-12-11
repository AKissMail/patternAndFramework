package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.entity.QuestionsEntity;
import de.gruppeo.wise2122_java_server.repository.QuestionsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions/all")
public class QuestionsController {

    private final QuestionsRepository questionsRepository;

    public QuestionsController(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;

    }

    @GetMapping("")
    public List<QuestionsEntity> index() {
        return questionsRepository.findAll();
    }
}

package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.entity.CategoryEntity;
import de.gruppeo.wise2122_java_server.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    @GetMapping("")
    public List<CategoryEntity> index() {
        return categoryRepository.findAll();
    }
}

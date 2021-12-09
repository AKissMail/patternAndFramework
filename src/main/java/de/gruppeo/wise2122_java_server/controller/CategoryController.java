package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.entity.Category;
import de.gruppeo.wise2122_java_server.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    @GetMapping("")
    public List<Category> index() {
        return categoryRepository.findAll();
    }
}

package com.hka.vs.CategoryService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hka.vs.CategoryService.model.Category;
import com.hka.vs.CategoryService.service.CategoryService;

@RestController
public class CategoryServiceController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

}

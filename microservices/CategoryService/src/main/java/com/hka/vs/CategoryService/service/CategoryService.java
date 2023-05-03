package com.hka.vs.CategoryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hka.vs.CategoryService.model.Category;
import com.hka.vs.CategoryService.repository.CategoryRepository;
import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        Iterable<?> iterable = (Iterable<?>) categoryRepository.findAll();
        for (Object category : iterable) {
            categories.add((Category) category);
        }
        return categories;
    }

}

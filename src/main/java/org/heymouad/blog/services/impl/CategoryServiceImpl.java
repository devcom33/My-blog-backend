package org.heymouad.blog.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.entities.Category;
import org.heymouad.blog.repositories.CategoryRepository;
import org.heymouad.blog.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists with name: " + category.getName());
        }
        return categoryRepository.save(category);
    }
}

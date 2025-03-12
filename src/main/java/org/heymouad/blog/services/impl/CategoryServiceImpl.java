package org.heymouad.blog.services.impl;

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
}

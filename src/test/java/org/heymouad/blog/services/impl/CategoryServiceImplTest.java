package org.heymouad.blog.services.impl;

import org.heymouad.blog.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void listCategories() {
    }

    @Test
    void createCategory() {
    }

    @Test
    void getCategoryById() {
    }

    @Test
    void deleteCategory() {
    }
}
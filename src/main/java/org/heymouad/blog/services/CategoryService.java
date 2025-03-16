package org.heymouad.blog.services;

import org.heymouad.blog.domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
    Category getCategoryById(UUID id);
    void deleteCategory(UUID categoryId);
}

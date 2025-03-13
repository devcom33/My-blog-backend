package org.heymouad.blog.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.dtos.CategoryDto;
import org.heymouad.blog.domain.dtos.CreateCategoryRequest;
import org.heymouad.blog.domain.entities.Category;
import org.heymouad.blog.domain.mappers.CategoryMapper;
import org.heymouad.blog.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories()
    {
        List<CategoryDto> categories = categoryService.listCategories().
                stream().map(categoryMapper::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategoryRequest(@Valid @RequestBody CreateCategoryRequest createCategoryRequest)
    {
        Category category = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(category);
        //Created POST endpoint returning HTTP 201 status for successful creation
        return new ResponseEntity<>(categoryMapper.toDto(savedCategory), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id)
    {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

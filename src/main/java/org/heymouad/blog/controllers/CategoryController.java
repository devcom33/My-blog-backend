package org.heymouad.blog.controllers;


import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.dtos.CategoryDto;
import org.heymouad.blog.domain.mappers.CategoryMapper;
import org.heymouad.blog.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public ResponseEntity<List<CategoryDto>> listCategories()
    {
        List<CategoryDto> categories = categoryService.listCategories().
                stream().map(categoryMapper::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }
}

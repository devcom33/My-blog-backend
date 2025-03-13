package org.heymouad.blog.controllers;


import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.dtos.TagDto;
import org.heymouad.blog.domain.entities.Tag;
import org.heymouad.blog.domain.mappers.TagMapper;
import org.heymouad.blog.services.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> getTags() {
        List<TagDto> tags = tagService.listTags().
                stream().map(tagMapper::entityToDto).collect(Collectors.toList());
        return ResponseEntity.ok(tags);
    }
}

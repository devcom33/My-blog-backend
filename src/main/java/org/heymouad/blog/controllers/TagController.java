package org.heymouad.blog.controllers;


import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.dtos.CreateTagRequest;
import org.heymouad.blog.domain.dtos.TagResponse;
import org.heymouad.blog.domain.entities.Tag;
import org.heymouad.blog.domain.mappers.TagMapper;
import org.heymouad.blog.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getTags() {
        List<Tag> tags = tagService.listTags();
        return ResponseEntity.ok(tagMapper.toTagResponseList(tags));
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTag(@RequestBody CreateTagRequest createTagsRequest) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());

        List<TagResponse> tagResponses = tagMapper.toTagResponseList(savedTags);

        return new ResponseEntity<>(
                tagResponses,
                HttpStatus.CREATED
        );
    }
}

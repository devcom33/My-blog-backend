package org.heymouad.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.dtos.PostDto;
import org.heymouad.blog.domain.entities.Post;
import org.heymouad.blog.domain.mappers.PostMapper;
import org.heymouad.blog.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v3/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping(path="/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable UUID id) {
        Post post = postService.getPost(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        PostDto postDto = postMapper.toDto(post);
        return ResponseEntity.ok(postDto);
    }

}

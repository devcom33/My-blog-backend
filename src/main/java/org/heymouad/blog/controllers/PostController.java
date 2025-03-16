package org.heymouad.blog.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.heymouad.blog.domain.PostRequest;
import org.heymouad.blog.domain.UpdatePostRequest;
import org.heymouad.blog.domain.dtos.PostRequestDto;
import org.heymouad.blog.domain.dtos.PostDto;
import org.heymouad.blog.domain.dtos.UpdatePostRequestDto;
import org.heymouad.blog.domain.entities.Post;
import org.heymouad.blog.domain.entities.User;
import org.heymouad.blog.domain.mappers.PostMapper;
import org.heymouad.blog.services.PostService;
import org.heymouad.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/posts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    /**
     * @RequestParam is used because categoryId and tagId are sent by the client in the URL query parameters.
     * @RequestAttribute would be used if these values were set earlier in the request lifecycle, such as in a filter or interceptor.
     */

    // Published Posts
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId
    ) {
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> responsePosts = posts.stream().map(postMapper::toDto).toList();

        return ResponseEntity.ok(responsePosts);
    }

    // Drafted Posts
    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDraftPosts(
            @RequestAttribute UUID userId
    ) {
        User author = userService.loadUserById(userId);
        List<Post> draftedPosts = postService.getDraftPosts(author);
        List<PostDto> responsePosts = draftedPosts.stream().map(postMapper::toDto).toList();

        return ResponseEntity.ok(responsePosts);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable UUID id) {
        Post post = postService.getPost(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        PostDto postDto = postMapper.toDto(post);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostRequestDto createPostRequestDto,
            @RequestAttribute UUID userId) {
        User author = userService.loadUserById(userId);
        PostRequest postRequest = postMapper.toPostRequest(createPostRequestDto);
        Post savedPost = postService.createPost(author, postRequest);

        return new ResponseEntity<>(postMapper.toDto(savedPost), HttpStatus.CREATED);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto) {
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
        Post updatedPost = postService.updatePost(id, updatePostRequest);
        PostDto updatedPostDto = postMapper.toDto(updatedPost);
        return ResponseEntity.ok(updatedPostDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
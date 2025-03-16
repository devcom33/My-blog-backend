package org.heymouad.blog.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.PostRequest;
import org.heymouad.blog.domain.PostStatus;
import org.heymouad.blog.domain.UpdatePostRequest;
import org.heymouad.blog.domain.entities.Category;
import org.heymouad.blog.domain.entities.Post;
import org.heymouad.blog.domain.entities.Tag;
import org.heymouad.blog.domain.entities.User;
import org.heymouad.blog.repositories.PostRepository;
import org.heymouad.blog.services.CategoryService;
import org.heymouad.blog.services.PostService;
import org.heymouad.blog.services.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;
    private static int word_per_minute = 125;

    @Override
    @Transactional
    public Post createPost(User user, PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setStatus(postRequest.getStatus());
        post.setAuthor(user);
        post.setReadingTime(calculateReadingTime(postRequest.getContent()));

        Set<UUID> tagsByIds = postRequest.getTagIds();
        post.setTags(new HashSet<>(tagService.getTagByIds(tagsByIds)));

        Category category = categoryService.getCategoryById(postRequest.getCategoryId());
        post.setCategory(category);

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(UUID userId, UpdatePostRequest updatePostRequest) {
        Post existingPost = postRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Post does not exist with id " + id));

        existingPost.setTitle(updatePostRequest.getTitle());
        existingPost.setContent(updatePostRequest.getContent());
        existingPost.setStatus(updatePostRequest.getStatus());
        existingPost.setReadingTime(calculateReadingTime(updatePostRequest.getContent()));

        UUID updatePostRequestCategoryId = updatePostRequest.getCategoryId();

        if(!existingPost.getCategory().getId().equals(updatePostRequestCategoryId)) {
            Category newCategory = categoryService.getCategoryById(updatePostRequestCategoryId);
            existingPost.setCategory(newCategory);
        }

        Set<UUID> existingTagIds = existingPost.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        Set<UUID> updatePostRequestTagIds = updatePostRequest.getTagIds();
        if(!existingTagIds.equals(updatePostRequestTagIds)) {
            List<Tag> newTags = tagService.getTagByIds(updatePostRequestTagIds);
            existingPost.setTags(new HashSet<>(newTags));
        }

        return postRepository.save(existingPost);
    }

    public int calculateReadingTime(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }

        int numWords = content.trim().split("\\s+").length;

        return Math.round((float) (numWords) / word_per_minute);
    }


    @Override
    @Transactional
    public void deletePost(UUID postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new EntityNotFoundException("Post does not exist with id " + id);
        }
        postRepository.deleteById(postId);
    }

    @Override
    public Post getPost(UUID postId) {
        return postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post does not exist with ID " + postId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {

        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
        }
        if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, tag);
        }

        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }
}

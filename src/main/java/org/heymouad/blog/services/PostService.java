package org.heymouad.blog.services;

import org.heymouad.blog.domain.PostRequest;
import org.heymouad.blog.domain.UpdatePostRequest;
import org.heymouad.blog.domain.entities.Post;
import org.heymouad.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post createPost(User user, PostRequest postRequest);
    Post updatePost(UUID userId, UpdatePostRequest updatePostRequest);
    void deletePost(UUID postId);
    Post getPost(UUID postId);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User userId);

}

package org.heymouad.blog.services;

import org.heymouad.blog.domain.PostRequest;
import org.heymouad.blog.domain.entities.Post;
import org.heymouad.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post createPost(User user, PostRequest postRequest);
    Post updatePost(Post post);
    void deletePost(UUID postId);
    Post getPost(UUID postId);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User userId);
    /*
    List<Post> getPostsByUser(Integer userId);
    List<Post> getPostsByTag(String tag);
    List<Post> getPostsByCategory(String category);
    List<Post> getPostsByTagAndCategory(String tag, String category);
    List<Post> getPostsByCategoryAndTag(String category, String tag);
    List<Post> getPostsByTagAndCategoryAndUser(String tag, String category, Integer userId);
    List<Post> getPostsByTagAndUserAndCategory(String tag, String category, Integer userId);
     */
}

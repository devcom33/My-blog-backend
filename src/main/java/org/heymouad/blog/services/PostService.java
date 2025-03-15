package org.heymouad.blog.services;

import org.heymouad.blog.domain.entities.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post createPost(Post post);
    Post updatePost(Post post);
    void deletePost(UUID postId);
    Post getPost(UUID postId);
    List<Post> getPosts();
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

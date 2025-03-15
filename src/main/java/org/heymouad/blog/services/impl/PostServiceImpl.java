package org.heymouad.blog.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.entities.Post;
import org.heymouad.blog.repositories.PostRepository;
import org.heymouad.blog.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public void deletePost(UUID postId) {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
        }
    }

    @Override
    public Post getPost(UUID postId) {
        return postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post does not exist with ID " + postId));
    }

    @Override
    public List<Post> getPosts() {
        return List.of();
    }
}

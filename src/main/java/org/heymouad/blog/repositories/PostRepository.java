package org.heymouad.blog.repositories;

import org.heymouad.blog.domain.PostStatus;
import org.heymouad.blog.domain.entities.Category;
import org.heymouad.blog.domain.entities.Post;
import org.heymouad.blog.domain.entities.Tag;
import org.heymouad.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByStatus(PostStatus status);
    //drafted
    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);
}

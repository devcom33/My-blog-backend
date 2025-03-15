package org.heymouad.blog.repositories;


import org.heymouad.blog.domain.entities.Category;
import org.heymouad.blog.domain.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    @Query("SELECT c FROM Tag c LEFT JOIN FETCH c.posts")
    List<Tag> findAllWithPostCount();
    List<Tag> findByNameIn(Set<String> names);
}

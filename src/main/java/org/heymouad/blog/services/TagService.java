package org.heymouad.blog.services;

import org.heymouad.blog.domain.entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {

    List<Tag> listTags();
    List<Tag> createTags(Set<String> tags);
}

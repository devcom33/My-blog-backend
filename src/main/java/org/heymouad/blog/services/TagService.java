package org.heymouad.blog.services;

import org.heymouad.blog.domain.entities.Tag;

import java.util.List;

public interface TagService {

    List<Tag> listTags();
    Tag createTag(Tag tag);
}

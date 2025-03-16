package org.heymouad.blog.services;

import org.heymouad.blog.domain.entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {

    List<Tag> listTags();
    Tag getTagById(UUID id);
    List<Tag> getTagByIds(Set<UUID> ids);
    List<Tag> createTags(Set<String> tags);
    void deleteTag(UUID tagId);
}

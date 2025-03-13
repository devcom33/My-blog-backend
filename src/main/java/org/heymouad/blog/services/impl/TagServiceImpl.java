package org.heymouad.blog.services.impl;

import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.entities.Tag;
import org.heymouad.blog.repositories.TagRepository;
import org.heymouad.blog.services.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public List<Tag> listTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Override
    public Tag createTag(Tag tag) {
        if (tagRepository.existsById(tag.getId())) {
            throw new IllegalArgumentException("Tag already exists");
        }
        return tagRepository.save(tag);
    }
}

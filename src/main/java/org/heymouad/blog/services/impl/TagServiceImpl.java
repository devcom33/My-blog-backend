package org.heymouad.blog.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.entities.Tag;
import org.heymouad.blog.repositories.TagRepository;
import org.heymouad.blog.services.TagService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public List<Tag> listTags() {
        return tagRepository.findAllWithPostCount();
    }
    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tags) {
        List<Tag> existingTags = tagRepository.findByNameIn(tags);

        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tags.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .toList();

        List<Tag> savedTags = new ArrayList<>();
        if(!newTags.isEmpty()) {
            savedTags = tagRepository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);

        return savedTags;
    }

    @Override
    public void deleteTag(UUID tagId) {
        Optional<Tag> tag = tagRepository.findById(tagId);
        if(tag.isPresent()) {
            if(!tag.get().getPosts().isEmpty()) {
                throw new IllegalStateException("Cannot Delete Tag With Posts");
            }
            tagRepository.delete(tag.get());
        }
    }

}

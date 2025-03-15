package org.heymouad.blog.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.entities.Tag;
import org.heymouad.blog.repositories.TagRepository;
import org.heymouad.blog.services.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

}

package org.heymouad.blog.domain.mappers;

import org.heymouad.blog.domain.PostStatus;
import org.heymouad.blog.domain.dtos.CreateTagRequest;
import org.heymouad.blog.domain.dtos.TagResponse;
import org.heymouad.blog.domain.entities.Post;
import org.heymouad.blog.domain.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target="postCount", source="posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);


    List<TagResponse> toTagResponseList(List<Tag> tags);

    @Named("calculatePostCount")
    default long calculatePostCount(Set<Post> posts) {
        if (posts == null) return 0;
        return posts.stream().filter(post -> post.getStatus() == PostStatus.PUBLISHED).count();
    }
}

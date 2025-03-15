package org.heymouad.blog.domain.mappers;


import org.heymouad.blog.domain.dtos.PostDto;
import org.heymouad.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostDto toDto(Post post);
}

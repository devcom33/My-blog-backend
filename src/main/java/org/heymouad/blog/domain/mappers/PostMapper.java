package org.heymouad.blog.domain.mappers;


import org.heymouad.blog.domain.PostRequest;
import org.heymouad.blog.domain.dtos.PostRequestDto;
import org.heymouad.blog.domain.dtos.PostDto;
import org.heymouad.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);
    PostRequest toPostRequest(PostRequestDto postRequestDto);
}

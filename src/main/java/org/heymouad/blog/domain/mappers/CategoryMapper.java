package org.heymouad.blog.domain.mappers;


import org.heymouad.blog.domain.PostStatus;
import org.heymouad.blog.domain.dtos.CategoryDto;
import org.heymouad.blog.domain.dtos.CreateCategoryRequest;
import org.heymouad.blog.domain.entities.Category;
import org.heymouad.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    /*
        - Creates a new CategoryDto instance.
        - Copies matching fields directly from Category to CategoryDto.
        - Calls calculatePostCount(category.getPosts()) and sets the result in CategoryDto.postCount.
     */
    @Mapping(target = "postCount", source="posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);
    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (posts == null) return 0;
        return posts.stream().filter(post -> post.getStatus() == PostStatus.PUBLISHED).count();
    }
}

package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import ru.arsentiev.dto.CategoryResponse;
import ru.arsentiev.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {
    CategoryResponse categoryToDto(Category category);

    Category dtoToCategory(CategoryResponse category);
}

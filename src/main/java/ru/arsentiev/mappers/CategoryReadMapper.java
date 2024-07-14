package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import ru.arsentiev.dto.CategoryReadDto;
import ru.arsentiev.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryReadMapper {
    CategoryReadDto categoryToDto(Category category);

    Category dtoToCategory(CategoryReadDto category);
}

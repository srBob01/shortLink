package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.CategoryReadDto;
import ru.arsentiev.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryReadMapper {
    CategoryReadMapper INSTANCE = Mappers.getMapper(CategoryReadMapper.class);

    CategoryReadDto categoryToDto(Category category);

    Category dtoToCategory(CategoryReadDto category);
}

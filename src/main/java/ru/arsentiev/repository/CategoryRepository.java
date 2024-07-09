package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arsentiev.dto.CategoryReadDto;
import ru.arsentiev.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Short> {
    Optional<CategoryReadDto> findByTitle(String title);
}

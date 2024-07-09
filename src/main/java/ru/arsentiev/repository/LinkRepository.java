package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.arsentiev.dto.LinkReadDto;
import ru.arsentiev.entity.Link;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query(value = "SELECT l.id, l.short_link, l.long_link, c.title " +
                   "FROM links l " +
                   "JOIN public.category c on c.id = l.id_category " +
                   "WHERE c.id = :idCategory", nativeQuery = true)
    List<LinkReadDto> findAllByCategory(Short idCategory);
}

package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.arsentiev.entity.Category;
import ru.arsentiev.entity.Link;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findAllByCategory(Category category);

    @Query(value = "SELECT COUNT(*) FROM links WHERE long_link = :longLink", nativeQuery = true)
    int countByLongLink(String longLink);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM links WHERE short_link = :shortLink)", nativeQuery = true)
    boolean existsByShortLink(String shortLink);
}

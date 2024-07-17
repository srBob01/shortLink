package ru.arsentiev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.arsentiev.entity.Category;
import ru.arsentiev.entity.Link;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long>,
        JpaSpecificationExecutor<Link>, QuerydslPredicateExecutor<Link> {
    List<Link> findAllByCategory(Category category);

    @Query(value = "SELECT COUNT(*) FROM links WHERE long_link = :longLink", nativeQuery = true)
    int countByLongLink(String longLink);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM links WHERE short_link = :shortLink)", nativeQuery = true)
    boolean existsByShortLink(String shortLink);

    @Query("select link " +
           "from Link link " +
           "where link.user.id != :userId")
    Page<Link> findAllDisplayedLinks(Pageable pageable, Integer userId);

    Optional<Link> findByShortLink(String shortLink);
}

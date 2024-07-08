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

    @Query(value = " SELECT l.* FROM links l  " +
                   "JOIN user_link ul on ul.id_link = l.id " +
                   "WHERE l.id_category = :idCategory AND ul.id_user = :idUser",
            nativeQuery = true)
    List<Link> findAllByIDCategoryAndIDUser(Short idCategory, Integer idUser);
}

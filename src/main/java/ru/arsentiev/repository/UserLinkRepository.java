package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.arsentiev.dto.UserLinkReadDto;
import ru.arsentiev.entity.UserLink;

import java.util.List;

@Repository
public interface UserLinkRepository extends JpaRepository<UserLink, Long> {
    @Query(value = "SELECT id, username, short_link, title, created_time, remove_time " +
                   "FROM user_link_main_info " +
                   "WHERE id_category = :idCategory AND id_user = :idUser", nativeQuery = true)
    List<UserLinkReadDto> findAllByIDCategoryAndIDUser(Short idCategory, Integer idUser);

    @Query(value = "SELECT id, username, short_link, title, created_time, remove_time " +
                   "FROM user_link_main_info " +
                   "WHERE id_category = :idCategory;",
            nativeQuery = true)
    List<UserLinkReadDto> findAllByIDCategory(Short idCategory);

    @Query(value = "SELECT id, username, short_link, title, created_time, remove_time " +
                   "FROM user_link_main_info " +
                   "WHERE id_user = :idUser;",
            nativeQuery = true)
    List<UserLinkReadDto> findAllByIDUser(Integer idUser);

    @Query(value = "SELECT id, username, short_link, title, created_time, remove_time " +
                   "FROM user_link_main_info;",
            nativeQuery = true)
    List<UserLinkReadDto> findAllIn();
}

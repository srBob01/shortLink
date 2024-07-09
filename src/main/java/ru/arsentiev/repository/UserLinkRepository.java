package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.arsentiev.dto.UserLinkReadDto;
import ru.arsentiev.entity.UserLink;

import java.util.List;

@Repository
public interface UserLinkRepository extends JpaRepository<UserLink, Long> {
    @Query(value = "SELECT ul.id, u.username, l.short_link, c.title, ul.created_time, ul.remove_time " +
                   "FROM user_link ul " +
                   "JOIN links l on ul.id_link = l.id " +
                   "JOIN public.category c on c.id = l.id_category " +
                   "JOIN public.users u on u.id = ul.id_user " +
                   "WHERE l.id_category = :idCategory AND ul.id_user = :idUser;",
            nativeQuery = true)
    List<UserLinkReadDto> findAllByIDCategoryAndIDUser(Short idCategory, Integer idUser);

    @Query(value = "SELECT ul.id, u.username, l.short_link, c.title, ul.created_time, ul.remove_time " +
                   "FROM user_link ul " +
                   "JOIN links l on ul.id_link = l.id " +
                   "JOIN public.category c on c.id = l.id_category " +
                   "JOIN public.users u on u.id = ul.id_user " +
                   "WHERE l.id_category = :idCategory;",
            nativeQuery = true)
    List<UserLinkReadDto> findAllByIDCategory(Short idCategory);

    @Query(value = "SELECT ul.id, u.username, l.short_link, c.title, ul.created_time, ul.remove_time " +
                   "FROM user_link ul " +
                   "JOIN links l on ul.id_link = l.id " +
                   "JOIN public.category c on c.id = l.id_category " +
                   "JOIN public.users u on u.id = ul.id_user " +
                   "WHERE ul.id_user = :idUser;",
            nativeQuery = true)
    List<UserLinkReadDto> findAllByIDUser(Integer idUser);

    @Query(value = "SELECT ul.id, u.username, l.short_link, c.title, ul.created_time, ul.remove_time " +
                   "FROM user_link ul " +
                   "JOIN links l on ul.id_link = l.id " +
                   "JOIN public.category c on c.id = l.id_category " +
                   "JOIN public.users u on u.id = ul.id_user;",
            nativeQuery = true)
    List<UserLinkReadDto> findAllIn();
}

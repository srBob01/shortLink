package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.arsentiev.entity.User;
import ru.arsentiev.entity.UserLink;

import java.util.List;

@Repository
public interface UserLinkRepository extends JpaRepository<UserLink, Long> {
    List<UserLink> findAllByUser(User user);
}

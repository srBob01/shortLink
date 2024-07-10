package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.arsentiev.entity.Role;
import ru.arsentiev.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,
        QuerydslPredicateExecutor<User> {
    Optional<User> findUserByUsername(String username);

    List<User> findAllByRoleOrderByUsernameDesc(Role role);
}

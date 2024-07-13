package ru.arsentiev.repository;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.arsentiev.dsl.QPredicates;
import ru.arsentiev.dsl.UserFilter;
import ru.arsentiev.entity.User;

import java.util.List;

import static ru.arsentiev.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class FilterUserRepository {
    private final EntityManager entityManager;

    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.firstName(), user.firstName::containsIgnoreCase)
                .add(filter.lastName(), user.lastName::containsIgnoreCase)
                .buildAnd();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}

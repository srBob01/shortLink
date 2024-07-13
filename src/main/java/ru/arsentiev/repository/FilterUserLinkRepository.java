package ru.arsentiev.repository;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.arsentiev.dsl.QPredicates;
import ru.arsentiev.dsl.UserLinkFilter;
import ru.arsentiev.entity.UserLink;

import java.util.List;

import static ru.arsentiev.entity.QCategory.category;
import static ru.arsentiev.entity.QLink.link;
import static ru.arsentiev.entity.QUser.user;
import static ru.arsentiev.entity.QUserLink.userLink;

@RequiredArgsConstructor
@Repository
public class FilterUserLinkRepository {
    private final EntityManager entityManager;

    public List<UserLink> findAllByFilter(UserLinkFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.username(), user.username::equalsIgnoreCase)
                .add(filter.titleCategory(), category.title::equalsIgnoreCase)
                .add(filter.linkName(), link.linkName::equalsIgnoreCase)
                .buildAnd();

        return new JPAQuery<UserLink>(entityManager)
                .select(userLink)
                .from(userLink)
                .join(userLink.user, user).fetchJoin()
                .join(userLink.link, link).fetchJoin()
                .join(link.category, category).fetchJoin()
                .where(predicate)
                .fetch();
    }
}

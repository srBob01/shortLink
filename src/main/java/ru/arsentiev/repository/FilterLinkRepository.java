package ru.arsentiev.repository;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.arsentiev.dsl.LinkFilter;
import ru.arsentiev.dsl.QPredicates;
import ru.arsentiev.entity.Link;

import java.util.List;

import static ru.arsentiev.entity.QCategory.category;
import static ru.arsentiev.entity.QLink.link;
import static ru.arsentiev.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class FilterLinkRepository {
    private final EntityManager entityManager;

    public List<Link> findAllByFilter(LinkFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.username(), user.username::equalsIgnoreCase)
                .add(filter.email(), user.email::containsIgnoreCase)
                .add(filter.titleCategory(), category.title::equalsIgnoreCase)
                .add(filter.linkName(), link.linkName::equalsIgnoreCase)
                .buildAnd();

        return new JPAQuery<Link>(entityManager)
                .from(link)
                .leftJoin(link.user, user)
                .leftJoin(link.category, category)
                .where(predicate)
                .fetch();
    }
}

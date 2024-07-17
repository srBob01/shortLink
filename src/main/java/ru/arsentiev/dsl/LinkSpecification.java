package ru.arsentiev.dsl;

import org.springframework.data.jpa.domain.Specification;
import ru.arsentiev.entity.Link;

public class LinkSpecification {
    public static Specification<Link> withUserId(Integer userId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("user").get("id"), userId
        ));
    }
}

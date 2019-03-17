package net.artemkv.referencewatches.specifications;

import net.artemkv.referencewatches.persistence.model.Watch;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WatchWithTitle implements Specification<Watch> {
    private String title;

    public WatchWithTitle(String title) {
        this.title = title;
    }

    @Override
    public Predicate toPredicate(
        Root<Watch> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        if (title == null || title.trim().length() == 0) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }
        return cb.like(
            cb.lower(root.get("title")),
            "%" + title.toLowerCase() + "%");
    }

    @Override
    public String toString() {
        if (title == null || title.trim().length() == 0) {
            return "WatchWithTitle: any";
        }
        return "WatchWithTitle: " + title;
    }
}

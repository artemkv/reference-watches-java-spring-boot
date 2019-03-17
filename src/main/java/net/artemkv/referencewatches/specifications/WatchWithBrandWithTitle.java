package net.artemkv.referencewatches.specifications;

import net.artemkv.referencewatches.persistence.model.Brand;
import net.artemkv.referencewatches.persistence.model.Watch;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WatchWithBrandWithTitle implements Specification<Watch> {
    private String brandTitle;

    public WatchWithBrandWithTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }

    @Override
    public Predicate toPredicate(
        Root<Watch> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        if (brandTitle == null || brandTitle.trim().length() == 0) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }
        Join<Watch, Brand> watchBrand = root.join("brand");
        return cb.like(
            cb.lower(watchBrand.get("title")),
            "%" + brandTitle.toLowerCase() + "%");
    }

    @Override
    public String toString() {
        if (brandTitle == null || brandTitle.trim().length() == 0) {
            return "WatchWithBrandWithTitle: any";
        }
        return "WatchWithBrandWithTitle: " + brandTitle;
    }
}

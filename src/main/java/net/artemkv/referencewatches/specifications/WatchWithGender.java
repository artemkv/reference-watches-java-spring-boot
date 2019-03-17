package net.artemkv.referencewatches.specifications;

import net.artemkv.referencewatches.persistence.model.Gender;
import net.artemkv.referencewatches.persistence.model.Watch;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WatchWithGender implements Specification<Watch> {
    private Gender gender;

    public WatchWithGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public Predicate toPredicate(
        Root<Watch> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        return cb.equal(root.get("gender"), gender);
    }

    @Override
    public String toString() {
        return "WatchWithGender: " + gender;
    }
}

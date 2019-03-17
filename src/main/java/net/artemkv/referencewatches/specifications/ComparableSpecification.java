package net.artemkv.referencewatches.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Decorator for the Specification<T> class.
 * Allows 2 specs to be compared, which is useful for writing tests.
 * @param <T>
 */
public class ComparableSpecification<T> implements Specification<T> {
    private Specification<T> spec;
    private StringBuilder sb = new StringBuilder();

    public ComparableSpecification<T> where(Specification<T> spec) {
        sb.append("where (" + spec.toString() + ") ");

        this.spec = spec;
        return this;
    }

    public ComparableSpecification<T> and(Specification<T> other) {
        sb.append("and (" + other.toString() + ") ");

        spec = spec.and(other);
        return this;
    }

    public ComparableSpecification<T> or(Specification<T> other) {
        sb.append("or (" + other.toString() + ") ");

        spec = spec.or(other);
        return this;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return spec.toPredicate(root, query, criteriaBuilder);
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return sb.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ComparableSpecification)) {
            return false;
        }

        ComparableSpecification<T> other = (ComparableSpecification<T>) obj;
        return sb.toString().equals(obj.toString());
    }
}

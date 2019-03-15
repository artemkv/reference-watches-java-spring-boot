package net.artemkv.referencewatches.persistence.repository;

import net.artemkv.referencewatches.persistence.model.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
}

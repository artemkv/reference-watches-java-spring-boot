package net.artemkv.referencewatches.service;

import net.artemkv.referencewatches.persistence.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {
    Page<Brand> getBrands(Pageable pageable);
    Brand getBrand(long id);
    Brand createBrand(Brand brand);
    boolean updateBrand(Brand brand);
}

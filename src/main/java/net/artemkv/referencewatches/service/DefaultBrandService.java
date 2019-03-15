package net.artemkv.referencewatches.service;

import net.artemkv.referencewatches.persistence.model.Brand;
import net.artemkv.referencewatches.persistence.repository.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultBrandService implements BrandService {
    private BrandRepository brandRepository;

    public DefaultBrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Page<Brand> getBrands(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Override
    public Brand getBrand(long id) {
        return brandRepository.findById(id).orElse(null);
    }
}

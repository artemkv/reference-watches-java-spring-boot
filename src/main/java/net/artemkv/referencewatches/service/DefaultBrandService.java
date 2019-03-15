package net.artemkv.referencewatches.service;

import net.artemkv.referencewatches.persistence.model.Brand;
import net.artemkv.referencewatches.persistence.repository.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    public Brand createBrand(Brand brand) {
        if (brand.getId() != 0) {
            throw new IllegalStateException("Id is generated by the database, should be empty.");
        }
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public boolean updateBrand(Brand brand) {
        if (brand.getId() == 0) {
            throw new IllegalStateException("Id of existing entity should be provided.");
        }
        Optional<Brand> brandInDb = brandRepository.findById(brand.getId());
        if (!brandInDb.isPresent()) {
            return false;
        }
        brandRepository.save(brand);
        return true;
    }
}

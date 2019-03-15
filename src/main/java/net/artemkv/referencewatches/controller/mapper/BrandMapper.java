package net.artemkv.referencewatches.controller.mapper;

import net.artemkv.referencewatches.dto.BrandDto;
import net.artemkv.referencewatches.persistence.model.Brand;

public final class BrandMapper {
    public static BrandDto makeBrandDto(Brand brand) {
        if (brand == null) {
            return null;
        }

        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setTitle(brand.getTitle());
        brandDto.setYearFounded(brand.getYearFounded());
        brandDto.setDescription(brand.getDescription());
        brandDto.setDateCreated(brand.getDateCreated());
        return brandDto;
    }
}

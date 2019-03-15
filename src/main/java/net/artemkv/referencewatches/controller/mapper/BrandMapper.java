package net.artemkv.referencewatches.controller.mapper;

import net.artemkv.referencewatches.dto.BrandDto;
import net.artemkv.referencewatches.dto.BrandToPostDto;
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

    public static Brand makeBrand(BrandToPostDto brandDto) {
        if (brandDto == null) {
            return null;
        }

        Brand brand = new Brand();
        brand.setTitle(brandDto.getTitle());
        brand.setYearFounded(brandDto.getYearFounded());
        brand.setDescription(brandDto.getDescription());
        return brand;
    }
}

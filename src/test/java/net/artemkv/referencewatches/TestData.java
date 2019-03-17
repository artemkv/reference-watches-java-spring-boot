package net.artemkv.referencewatches;

import net.artemkv.referencewatches.dto.BrandDto;
import net.artemkv.referencewatches.dto.WatchDto;
import net.artemkv.referencewatches.dto.WatchToPostDto;
import net.artemkv.referencewatches.dto.WatchToPutDto;
import net.artemkv.referencewatches.persistence.model.Brand;
import net.artemkv.referencewatches.persistence.model.CaseMaterial;
import net.artemkv.referencewatches.persistence.model.Gender;
import net.artemkv.referencewatches.persistence.model.Movement;
import net.artemkv.referencewatches.persistence.model.Watch;

public class TestData {
    public static Brand getBrandRolex(Long id) {
        Brand brand = new Brand();
        if (id != null) {
            brand.setId(id);
        }
        brand.setTitle("Rolex");
        brand.setYearFounded(1915);
        brand.setDescription("Swiss luxury watch manufacturer");
        return brand;
    }

    public static Brand getBrandPiguet(Long id) {
        Brand brand = new Brand();
        if (id != null) {
            brand.setId(id);
        }
        brand.setTitle("Audemars Piguet");
        brand.setYearFounded(1875);
        brand.setDescription("Swiss manufacturer of luxury mechanical watches and clocks");
        return brand;
    }

    public static Movement getMovementAutomatic(Long id) {
        Movement movement = new Movement();
        if (id != null) {
            movement.setId(id);
        }
        movement.setTitle("Automatic");
        return movement;
    }

    public static Watch getWatchSeaDweller(Long brandId, Long movementId, Long id) {
        Brand brand = getBrandRolex(brandId);
        Movement movement = getMovementAutomatic(movementId);

        Watch watch = new Watch();
        if (id != null) {
            watch.setId(id);
        }
        watch.setModel("123ROLEX");
        watch.setTitle("Sea-Dweller");
        watch.setGender(Gender.MENS);
        watch.setCaseSize(43);
        watch.setCaseMaterial(CaseMaterial.STAINLESS_STEEL);
        watch.setBrand(brand);
        watch.setBrandId(watch.getBrand().getId());
        watch.setMovement(movement);
        watch.setMovementId(watch.getMovement().getId());
        return watch;
    }

    public static Watch getWatchRoyalOak(Long brandId, Long movementId, Long id) {
        Brand brand = getBrandPiguet(brandId);
        Movement movement = getMovementAutomatic(movementId);

        Watch watch = new Watch();
        if (id != null) {
            watch.setId(id);
        }
        watch.setModel("15450BA");
        watch.setTitle("Royal Oak");
        watch.setGender(Gender.LADIES);
        watch.setCaseSize(37);
        watch.setCaseMaterial(CaseMaterial.GOLD);
        watch.setBrand(brand);
        watch.setBrandId(watch.getBrand().getId());
        watch.setMovement(movement);
        watch.setMovementId(watch.getMovement().getId());
        return watch;
    }

    public static BrandDto getBrandDtoRolex() {
        BrandDto brand = new BrandDto();
        brand.setId(234);
        brand.setTitle("Rolex");
        brand.setYearFounded(1915);
        brand.setDescription("Swiss luxury watch manufacturer");
        return brand;
    }

    public static WatchDto getWatchDtoExplorer() {
        WatchDto watchDto = new WatchDto();
        watchDto.setId(123);
        watchDto.setModel("123ROLEX");
        watchDto.setTitle("Sea-Dweller");
        watchDto.setGender(Gender.MENS);
        watchDto.setCaseSize(43);
        watchDto.setCaseMaterial(CaseMaterial.STAINLESS_STEEL);
        watchDto.setBrand(getBrandDtoRolex());
        watchDto.setMovementId(345);
        return watchDto;
    }

    public static WatchToPostDto getWatchToPostDtoExplorer() {
        WatchToPostDto watchDto = new WatchToPostDto();
        watchDto.setModel("123ROLEX");
        watchDto.setTitle("Sea-Dweller");
        watchDto.setGender(Gender.MENS);
        watchDto.setCaseSize(43);
        watchDto.setCaseMaterial(CaseMaterial.STAINLESS_STEEL);
        watchDto.setBrandId(234);
        watchDto.setMovementId(345);
        return watchDto;
    }

    public static WatchToPutDto getWatchToPutDtoExplorer() {
        WatchToPutDto watchDto = new WatchToPutDto();
        watchDto.setId(123);
        watchDto.setModel("123ROLEX");
        watchDto.setTitle("Sea-Dweller");
        watchDto.setGender(Gender.MENS);
        watchDto.setCaseSize(43);
        watchDto.setCaseMaterial(CaseMaterial.STAINLESS_STEEL);
        watchDto.setBrandId(234);
        watchDto.setMovementId(345);
        return watchDto;
    }
}

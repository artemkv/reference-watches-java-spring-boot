package net.artemkv.referencewatches.controller.mapper;

import net.artemkv.referencewatches.dto.WatchDto;
import net.artemkv.referencewatches.persistence.model.Watch;

public final class WatchMapper {
    public static WatchDto makeWatchDto(Watch watch) {
        if (watch == null) {
            return null;
        }

        WatchDto watchDto = new WatchDto();
        watchDto.setId(watch.getId());
        watchDto.setModel(watch.getModel());
        watchDto.setTitle(watch.getTitle());
        watchDto.setGender(watch.getGender());
        watchDto.setCaseSize(watch.getCaseSize());
        watchDto.setCaseMaterial(watch.getCaseMaterial());
        watchDto.setDateCreated(watch.getDateCreated());
        watchDto.setBrand(BrandMapper.makeBrandDto(watch.getBrand()));
        watchDto.setMovementId(watch.getMovement().getId());
        return watchDto;
    }
}

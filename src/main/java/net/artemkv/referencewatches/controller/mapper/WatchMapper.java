package net.artemkv.referencewatches.controller.mapper;

import net.artemkv.referencewatches.dto.WatchDto;
import net.artemkv.referencewatches.dto.WatchToPostDto;
import net.artemkv.referencewatches.dto.WatchToPutDto;
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
        watchDto.setMovementId(watch.getMovementId());
        return watchDto;
    }

    public static Watch makeWatch(WatchToPostDto watchDto) {
        if (watchDto == null) {
            return null;
        }

        Watch watch = new Watch();
        watch.setModel(watchDto.getModel());
        watch.setTitle(watchDto.getTitle());
        watch.setGender(watchDto.getGender());
        watch.setCaseSize(watchDto.getCaseSize());
        watch.setCaseMaterial(watchDto.getCaseMaterial());
        watch.setBrandId(watchDto.getBrandId());
        watch.setMovementId(watchDto.getMovementId());
        return watch;
    }

    public static Watch makeWatch(WatchToPutDto watchDto) {
        if (watchDto == null) {
            return null;
        }

        Watch watch = new Watch();
        watch.setId(watchDto.getId());
        watch.setModel(watchDto.getModel());
        watch.setTitle(watchDto.getTitle());
        watch.setGender(watchDto.getGender());
        watch.setCaseSize(watchDto.getCaseSize());
        watch.setCaseMaterial(watchDto.getCaseMaterial());
        watch.setBrandId(watchDto.getBrandId());
        watch.setMovementId(watchDto.getMovementId());
        return watch;
    }
}

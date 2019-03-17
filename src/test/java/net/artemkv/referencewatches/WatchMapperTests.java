package net.artemkv.referencewatches;

import net.artemkv.referencewatches.controller.mapper.WatchMapper;
import net.artemkv.referencewatches.dto.WatchDto;
import net.artemkv.referencewatches.dto.WatchToPostDto;
import net.artemkv.referencewatches.dto.WatchToPutDto;
import net.artemkv.referencewatches.persistence.model.Watch;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WatchMapperTests {
    @Test
    public void testMakeWatchDto() {
        // given
        Watch watch = TestData.getWatchSeaDweller(1L, 2L, 3L);

        // when
        WatchDto watchDto = WatchMapper.makeWatchDto(watch);

        // then
        assertThat(watch.getId()).isEqualTo(watchDto.getId());
        assertThat(watch.getModel()).isEqualTo(watchDto.getModel());
        assertThat(watch.getTitle()).isEqualTo(watchDto.getTitle());
        assertThat(watch.getGender()).isEqualTo(watchDto.getGender());
        assertThat(watch.getCaseSize()).isEqualTo(watchDto.getCaseSize());
        assertThat(watch.getCaseMaterial()).isEqualTo(watchDto.getCaseMaterial());
        assertThat(watch.getMovementId()).isEqualTo(watchDto.getMovementId());

        assertThat(watch.getBrand().getId()).isEqualTo(watchDto.getBrand().getId());
        assertThat(watch.getBrand().getTitle()).isEqualTo(watchDto.getBrand().getTitle());
        assertThat(watch.getBrand().getYearFounded()).isEqualTo(watchDto.getBrand().getYearFounded());
        assertThat(watch.getBrand().getDescription()).isEqualTo(watchDto.getBrand().getDescription());
    }

    @Test
    public void testMakeWatchFromWatchToPost() {
        // given
        WatchToPostDto watchDto = TestData.getWatchToPostDtoExplorer();

        // when
        Watch watch = WatchMapper.makeWatch(watchDto);

        // then
        assertThat(watchDto.getModel()).isEqualTo(watch.getModel());
        assertThat(watchDto.getTitle()).isEqualTo(watch.getTitle());
        assertThat(watchDto.getGender()).isEqualTo(watch.getGender());
        assertThat(watchDto.getCaseSize()).isEqualTo(watch.getCaseSize());
        assertThat(watchDto.getCaseMaterial()).isEqualTo(watch.getCaseMaterial());
        assertThat(watchDto.getBrandId()).isEqualTo(watch.getBrandId());
        assertThat(watchDto.getMovementId()).isEqualTo(watch.getMovementId());
    }

    @Test
    public void testMakeWatchFromWatchToPut() {
        // given
        WatchToPutDto watchDto = TestData.getWatchToPutDtoExplorer();

        // when
        Watch watch = WatchMapper.makeWatch(watchDto);

        // then
        assertThat(watchDto.getId()).isEqualTo(watch.getId());
        assertThat(watchDto.getModel()).isEqualTo(watch.getModel());
        assertThat(watchDto.getTitle()).isEqualTo(watch.getTitle());
        assertThat(watchDto.getGender()).isEqualTo(watch.getGender());
        assertThat(watchDto.getCaseSize()).isEqualTo(watch.getCaseSize());
        assertThat(watchDto.getCaseMaterial()).isEqualTo(watch.getCaseMaterial());
        assertThat(watchDto.getBrandId()).isEqualTo(watch.getBrandId());
        assertThat(watchDto.getMovementId()).isEqualTo(watch.getMovementId());
    }
}

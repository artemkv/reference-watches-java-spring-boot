package net.artemkv.referencewatches;

import net.artemkv.referencewatches.persistence.model.Gender;
import net.artemkv.referencewatches.persistence.model.Watch;
import net.artemkv.referencewatches.persistence.repository.WatchRepository;
import net.artemkv.referencewatches.service.DefaultWatchService;
import net.artemkv.referencewatches.service.WatchService;
import net.artemkv.referencewatches.specifications.ComparableSpecification;
import net.artemkv.referencewatches.specifications.WatchWithBrandWithTitle;
import net.artemkv.referencewatches.specifications.WatchWithGender;
import net.artemkv.referencewatches.specifications.WatchWithTitle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class DefaultWatchServiceTests {
    @TestConfiguration
    static class DefaultWatchServiceTestContextConfiguration {
        @MockBean
        private static WatchRepository watchRepository;

        @MockBean
        private static EntityManager entityManager;

        @Bean
        public WatchService watchService() {
            return new DefaultWatchService(watchRepository, entityManager);
        }
    }

    @Autowired
    private WatchService watchService;

    @MockBean
    private Pageable pageable;

    @Test
    public void testFindById() {
        // given
        Watch watch = TestData.getWatchSeaDweller(123L, 234L, 345L);
        Mockito
            .when(DefaultWatchServiceTestContextConfiguration.watchRepository
                .findById(watch.getId()))
            .thenReturn(Optional.of(watch));

        // when
        Watch found = watchService.getWatch(watch.getId());

        // then
        assertThat(found.getId()).isEqualTo(watch.getId());
    }

    @Test
    public void testFindByTitle() {
        // given
        Watch watch1 = TestData.getWatchSeaDweller(123L, 234L, 345L);
        Watch watch2 = TestData.getWatchRoyalOak(456L, 567L, 678L);

        ComparableSpecification<Watch> spec = new ComparableSpecification<Watch>()
            .where(new WatchWithTitle("sea"))
            .and(new WatchWithGender(Gender.MENS))
            .and(new WatchWithBrandWithTitle(null));

        List<Watch> watches = new ArrayList<>();
        watches.add(watch1);
        Mockito
            .when(DefaultWatchServiceTestContextConfiguration.watchRepository.findAll(
                org.mockito.ArgumentMatchers.eq(spec),
                org.mockito.ArgumentMatchers.any(Pageable.class)))
            .thenReturn(new PageImpl<Watch>(watches));

        // when
        Page<Watch> page = watchService.getWatches("sea", Gender.MENS, null, pageable);
        List<Watch> found = page.stream().collect(Collectors.toList());

        // then
        assertThat(found.get(0).getId()).isEqualTo(watch1.getId());
    }

    @Test
    public void testFindByGender() {
        // given
        Watch watch1 = TestData.getWatchSeaDweller(123L, 234L, 345L);
        Watch watch2 = TestData.getWatchRoyalOak(456L, 567L, 678L);

        ComparableSpecification<Watch> spec = new ComparableSpecification<Watch>()
            .where(new WatchWithTitle(null))
            .and(new WatchWithGender(Gender.LADIES))
            .and(new WatchWithBrandWithTitle(null));

        List<Watch> watches = new ArrayList<>();
        watches.add(watch2);
        Mockito
            .when(DefaultWatchServiceTestContextConfiguration.watchRepository.findAll(
                org.mockito.ArgumentMatchers.eq(spec),
                org.mockito.ArgumentMatchers.any(Pageable.class)))
            .thenReturn(new PageImpl<Watch>(watches));

        // when
        Page<Watch> page = watchService.getWatches(null, Gender.LADIES, null, pageable);
        List<Watch> found = page.stream().collect(Collectors.toList());

        // then
        assertThat(found.get(0).getId()).isEqualTo(watch2.getId());
    }

    @Test
    public void testCreate() {
        // given
        Watch watch = TestData.getWatchSeaDweller(null, null, null);
        Mockito.when(DefaultWatchServiceTestContextConfiguration.watchRepository.save(watch))
            .thenReturn(watch);

        // when
        Watch found = watchService.createWatch(watch);

        // then
        assertThat(found.getId()).isEqualTo(watch.getId());
        assertThat(found.getTitle()).isEqualTo(watch.getTitle());
    }

    @Test
    public void testUpdate() {
        // given
        Watch watch = TestData.getWatchSeaDweller(123L, 234L, 345L);
        Watch watchUpdated = TestData.getWatchSeaDweller(123L, 234L, 345L);
        watchUpdated.setTitle(watch.getTitle() + " updated");
        Mockito
            .when(DefaultWatchServiceTestContextConfiguration.watchRepository
                .findById(watch.getId()))
            .thenReturn(Optional.of(watch));
        Mockito
            .when(DefaultWatchServiceTestContextConfiguration.watchRepository
                .save(watchUpdated))
            .thenReturn(watchUpdated);

        // when
        boolean result = watchService.updateWatch(watchUpdated);

        // then
        verify(DefaultWatchServiceTestContextConfiguration.watchRepository).save(watchUpdated);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testCouldNotUpdate() {
        // given
        Watch watchUpdated = TestData.getWatchSeaDweller(123L, 234L, 345L);
        watchUpdated.setTitle(watchUpdated.getTitle() + "updated");
        Mockito
            .when(DefaultWatchServiceTestContextConfiguration.watchRepository
                .findById(watchUpdated.getId()))
            .thenReturn(Optional.empty());

        // when
        boolean result = watchService.updateWatch(watchUpdated);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testDelete() {
        // given
        Watch watch = TestData.getWatchSeaDweller(123L, 234L, 345L);
        Mockito
            .when(DefaultWatchServiceTestContextConfiguration.watchRepository
                .findById(watch.getId()))
            .thenReturn(Optional.of(watch));

        // when
        boolean result = watchService.deleteWatch(watch.getId());

        // then
        verify(DefaultWatchServiceTestContextConfiguration.watchRepository).deleteById(watch.getId());
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testCouldNotDelete() {
        // given
        Watch watch = TestData.getWatchSeaDweller(123L, 234L, 345L);
        Mockito
            .when(DefaultWatchServiceTestContextConfiguration.watchRepository
                .findById(watch.getId()))
            .thenReturn(Optional.empty());

        // when
        boolean result = watchService.deleteWatch(watch.getId());

        // then
        assertThat(result).isEqualTo(false);
    }
}

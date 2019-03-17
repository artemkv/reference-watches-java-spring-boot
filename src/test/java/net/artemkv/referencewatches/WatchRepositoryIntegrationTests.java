package net.artemkv.referencewatches;

import net.artemkv.referencewatches.persistence.model.Gender;
import net.artemkv.referencewatches.persistence.model.Watch;
import net.artemkv.referencewatches.persistence.repository.WatchRepository;
import net.artemkv.referencewatches.specifications.WatchWithBrandWithTitle;
import net.artemkv.referencewatches.specifications.WatchWithGender;
import net.artemkv.referencewatches.specifications.WatchWithTitle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WatchRepositoryIntegrationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WatchRepository watchRepository;

    @Test
    public void testFindById() {
        // given
        Watch watch = TestData.getWatchSeaDweller(null, null, null);
        entityManager.persist(watch.getBrand());
        entityManager.persist(watch.getMovement());
        watch.setBrandId(watch.getBrand().getId());
        watch.setMovementId(watch.getMovement().getId());
        entityManager.persist(watch);

        entityManager.flush();

        // when
        Watch found = watchRepository.findById(watch.getId()).get();

        // then
        assertThat(found.getTitle()).isEqualTo(watch.getTitle());
    }

    @Test
    public void testFindByTitle() {
        // given
        Watch watch1 = TestData.getWatchSeaDweller(null, null, null);
        entityManager.persist(watch1.getBrand());
        entityManager.persist(watch1.getMovement());
        watch1.setBrandId(watch1.getBrand().getId());
        watch1.setMovementId(watch1.getMovement().getId());
        entityManager.persist(watch1);

        Watch watch2 = TestData.getWatchRoyalOak(null, null, null);
        entityManager.persist(watch2.getBrand());
        entityManager.persist(watch2.getMovement());
        watch2.setBrandId(watch2.getBrand().getId());
        watch2.setMovementId(watch2.getMovement().getId());
        entityManager.persist(watch2);

        entityManager.flush();

        // when
        Specification<Watch> spec = Specification
            .where(new WatchWithTitle("sea"));
        List<Watch> found = watchRepository.findAll(spec);

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getTitle()).isEqualTo(watch1.getTitle());
    }

    @Test
    public void testFindByGender() {
        // given
        Watch watch1 = TestData.getWatchSeaDweller(null, null, null);
        entityManager.persist(watch1.getBrand());
        entityManager.persist(watch1.getMovement());
        watch1.setBrandId(watch1.getBrand().getId());
        watch1.setMovementId(watch1.getMovement().getId());
        entityManager.persist(watch1);

        Watch watch2 = TestData.getWatchRoyalOak(null, null, null);
        entityManager.persist(watch2.getBrand());
        entityManager.persist(watch2.getMovement());
        watch2.setBrandId(watch2.getBrand().getId());
        watch2.setMovementId(watch2.getMovement().getId());
        entityManager.persist(watch2);

        entityManager.flush();

        // when
        Specification<Watch> spec = Specification
            .where(new WatchWithGender(Gender.LADIES));
        List<Watch> found = watchRepository.findAll(spec);

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getTitle()).isEqualTo(watch2.getTitle());
    }

    @Test
    public void testFindByBrandTitle() {
        // given
        Watch watch1 = TestData.getWatchSeaDweller(null, null, null);
        entityManager.persist(watch1.getBrand());
        entityManager.persist(watch1.getMovement());
        watch1.setBrandId(watch1.getBrand().getId());
        watch1.setMovementId(watch1.getMovement().getId());
        entityManager.persist(watch1);

        Watch watch2 = TestData.getWatchRoyalOak(null, null, null);
        entityManager.persist(watch2.getBrand());
        entityManager.persist(watch2.getMovement());
        watch2.setBrandId(watch2.getBrand().getId());
        watch2.setMovementId(watch2.getMovement().getId());
        entityManager.persist(watch2);

        entityManager.flush();

        // when
        Specification<Watch> spec = Specification
            .where(new WatchWithBrandWithTitle("piguet"));
        List<Watch> found = watchRepository.findAll(spec);

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getTitle()).isEqualTo(watch2.getTitle());
    }
}
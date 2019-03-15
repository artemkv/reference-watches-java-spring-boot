package net.artemkv.referencewatches.persistence.repository;

import net.artemkv.referencewatches.persistence.model.Watch;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WatchRepository extends PagingAndSortingRepository<Watch, Long> {
}

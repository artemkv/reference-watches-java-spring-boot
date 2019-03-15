package net.artemkv.referencewatches.service;

import net.artemkv.referencewatches.persistence.model.Watch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WatchService {
    Page<Watch> getWatches(Pageable pageable);
}

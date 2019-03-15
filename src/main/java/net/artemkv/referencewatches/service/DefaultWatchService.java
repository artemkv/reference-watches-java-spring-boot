package net.artemkv.referencewatches.service;

import net.artemkv.referencewatches.persistence.model.Watch;
import net.artemkv.referencewatches.persistence.repository.WatchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultWatchService implements WatchService {
    private WatchRepository watchRepository;

    public DefaultWatchService(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @Override
    public Page<Watch> getWatches(Pageable pageable) {
        return watchRepository.findAll(pageable);
    }
}

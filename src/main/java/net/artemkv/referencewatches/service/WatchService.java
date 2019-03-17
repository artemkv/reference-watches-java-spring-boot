package net.artemkv.referencewatches.service;

import net.artemkv.referencewatches.persistence.model.Gender;
import net.artemkv.referencewatches.persistence.model.Watch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WatchService {
    Page<Watch> getWatches(String title, Gender gender, String brandTitle, Pageable pageable);
    Watch getWatch(long id);
    Watch createWatch(Watch watch);
    boolean updateWatch(Watch watch);
    boolean deleteWatch(long id);
}

package net.artemkv.referencewatches.persistence.repository;

import net.artemkv.referencewatches.persistence.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WatchRepository
    extends JpaRepository<Watch, Long>, JpaSpecificationExecutor<Watch> {
}

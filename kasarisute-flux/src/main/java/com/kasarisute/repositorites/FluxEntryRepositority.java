package com.kasarisute.repositorites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kasarisute.entitys.FluxEntry;

public interface FluxEntryRepositority
        extends JpaRepository<FluxEntry, Long>, JpaSpecificationExecutor<FluxEntry> {
            
                
}

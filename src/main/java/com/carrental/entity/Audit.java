package com.carrental.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Embeddable
public class Audit {

    private LocalDateTime date_created;
    private LocalDateTime last_updated;

    @PrePersist
    void prePersist() {
        date_created = LocalDateTime.now();
    }
    @PreUpdate
    void preUpdate() {
        last_updated = LocalDateTime.now();
    }
}

package com.store.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class AbstractAuditModel {
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private ZonedDateTime modifiedAt;
}

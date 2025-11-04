package com.bank.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity used to store audit logs about profile modifications.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit", schema = "profile")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_type", nullable = false, length = 40)
    private String entityType;

    @Column(name = "operation_type", nullable = false, length = 255)
    private String operationType;

    @Column(name = "created_by", nullable = false, length = 255)
    private String createdBy;

    @Column(name = "modified_by", length = 255)
    private String modifiedBy;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "modified_at")
    private OffsetDateTime modifiedAt;

    @Column(name = "new_entity_json", columnDefinition = "TEXT")
    private String newEntityJson;

    @Column(name = "entity_json", nullable = false, columnDefinition = "TEXT")
    private String entityJson;
}

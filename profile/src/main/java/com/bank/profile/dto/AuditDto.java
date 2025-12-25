package com.bank.profile.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO describing audit log entries.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {

    private Long id;

    private String entityType;

    private String operationType;

    private String createdBy;

    private String modifiedBy;

    private OffsetDateTime createdAt;

    private OffsetDateTime modifiedAt;

    private String newEntityJson;

    private String entityJson;
}

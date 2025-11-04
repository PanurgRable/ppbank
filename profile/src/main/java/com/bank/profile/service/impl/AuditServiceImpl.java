package com.bank.profile.service.impl;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.Audit;
import com.bank.profile.mapper.AuditMapper;
import com.bank.profile.repository.AuditRepository;
import com.bank.profile.service.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link AuditService} that persists audit events via {@link AuditRepository}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuditServiceImpl implements AuditService {

    private static final String SYSTEM_ACTOR = "SYSTEM";

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void logCreate(String entityType, Object newState) {
        persistAudit(entityType, "CREATE", null, newState);
    }

    @Override
    public void logUpdate(String entityType, Object previousState, Object newState) {
        persistAudit(entityType, "UPDATE", previousState, newState);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditDto> getAll() {
        return auditRepository.findAll().stream()
                .map(auditMapper::toDto)
                .collect(Collectors.toList());
    }

    private void persistAudit(String entityType, String operationType, Object previousState, Object newState) {
        try {
            String entityJson = toJson(previousState != null ? previousState : newState);
            String newEntityJson = newState != null ? toJson(newState) : null;
            OffsetDateTime now = OffsetDateTime.now();
            Audit audit = Audit.builder()
                    .entityType(entityType)
                    .operationType(operationType)
                    .createdBy(SYSTEM_ACTOR)
                    .modifiedBy(previousState != null ? SYSTEM_ACTOR : null)
                    .createdAt(now)
                    .modifiedAt(previousState != null ? now : null)
                    .entityJson(entityJson)
                    .newEntityJson(newEntityJson)
                    .build();
            auditRepository.save(audit);
        } catch (JsonProcessingException exception) {
            log.error("Unable to serialize audit payload for entity {}", entityType, exception);
        }
    }

    private String toJson(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }
}

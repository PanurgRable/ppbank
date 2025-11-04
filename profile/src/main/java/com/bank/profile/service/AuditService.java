package com.bank.profile.service;

import com.bank.profile.dto.AuditDto;
import java.util.List;

/**
 * Service for storing audit events in the audit table.
 */
public interface AuditService {

    void logCreate(String entityType, Object newState);

    void logUpdate(String entityType, Object previousState, Object newState);

    List<AuditDto> getAll();
}

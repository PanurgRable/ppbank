package com.bank.profile.audit;

import com.bank.profile.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Aspect that captures create and update operations for audit purposes.
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditService auditService;

    @Pointcut("@annotation(auditable)")
    public void auditableOperation(Auditable auditable) {
        // pointcut definition
    }

    @Around(value = "auditableOperation(auditable)")
    public Object captureAuditEvent(ProceedingJoinPoint joinPoint, Auditable auditable) throws Throwable {
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            AuditContextHolder.clear();
            throw throwable;
        }

        OperationType operationType = auditable.operation();
        String entityType = auditable.entity();
        Object previousState = AuditContextHolder.getPreviousState();

        try {
            switch (operationType) {
                case CREATE -> logCreate(entityType, result);
                case UPDATE -> logUpdate(entityType, previousState, result);
                default -> log.debug("Skipping unsupported audit operation {} for entity {}", operationType, entityType);
            }
        } finally {
            AuditContextHolder.clear();
        }

        return result;
    }

    private void logCreate(String entityType, Object newState) {
        if (newState == null) {
            log.warn("Audit create event skipped for entity {} because result is null", entityType);
            return;
        }
        auditService.logCreate(entityType, newState);
    }

    private void logUpdate(String entityType, Object previousState, Object newState) {
        if (newState == null) {
            log.warn("Audit update event skipped for entity {} because result is null", entityType);
            return;
        }
        if (previousState == null) {
            log.warn("Audit update event for entity {} lacks previous state; logging new state only", entityType);
        }
        auditService.logUpdate(entityType, previousState, newState);
    }
}

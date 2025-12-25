package com.bank.profile.repository;

import com.bank.profile.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for working with {@link Audit} entities.
 */
public interface AuditRepository extends JpaRepository<Audit, Long> {
}

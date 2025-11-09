package com.bank.profile.repository;

import com.bank.profile.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for working with {@link Passport} entities.
 */
public interface PassportRepository extends JpaRepository<Passport, Long> {
}

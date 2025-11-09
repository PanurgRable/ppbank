package com.bank.profile.repository;

import com.bank.profile.entity.ActualRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for working with {@link ActualRegistration} entities.
 */
public interface ActualRegistrationRepository extends JpaRepository<ActualRegistration, Long> {
}

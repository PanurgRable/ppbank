package com.bank.profile.repository;

import com.bank.profile.entity.AccountDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for working with {@link AccountDetails} entities.
 */
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {

    List<AccountDetails> findByProfile_Id(Long profileId);
}

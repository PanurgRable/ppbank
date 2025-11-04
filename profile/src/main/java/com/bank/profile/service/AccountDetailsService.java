package com.bank.profile.service;

import com.bank.profile.dto.AccountDetailsDto;
import java.util.List;

/**
 * Service for managing account detail entities.
 */
public interface AccountDetailsService {

    AccountDetailsDto create(AccountDetailsDto dto);

    AccountDetailsDto update(Long id, AccountDetailsDto dto);

    void delete(Long id);

    AccountDetailsDto getById(Long id);

    List<AccountDetailsDto> getAll();

    List<AccountDetailsDto> getByProfileId(Long profileId);
}

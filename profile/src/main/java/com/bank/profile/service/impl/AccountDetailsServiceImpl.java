package com.bank.profile.service.impl;

import com.bank.profile.audit.AuditContextHolder;
import com.bank.profile.audit.Auditable;
import com.bank.profile.audit.OperationType;
import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entity.AccountDetails;
import com.bank.profile.entity.Profile;
import com.bank.profile.mapper.AccountDetailsMapper;
import com.bank.profile.repository.AccountDetailsRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.AccountDetailsService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link AccountDetailsService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountDetailsServiceImpl implements AccountDetailsService {

    private final AccountDetailsRepository accountDetailsRepository;
    private final AccountDetailsMapper accountDetailsMapper;
    private final ProfileRepository profileRepository;

    @Override
    @Auditable(entity = "AccountDetails", operation = OperationType.CREATE)
    public AccountDetailsDto create(AccountDetailsDto dto) {
        log.info("Creating account details for account {}", dto.getAccountId());
        Profile profile = getProfile(dto.getProfileId());
        AccountDetails accountDetails = accountDetailsMapper.toEntity(dto);
        accountDetails.setProfile(profile);
        AccountDetails saved = accountDetailsRepository.save(accountDetails);
        return accountDetailsMapper.toDto(saved);
    }

    @Override
    @Auditable(entity = "AccountDetails", operation = OperationType.UPDATE)
    public AccountDetailsDto update(Long id, AccountDetailsDto dto) {
        log.info("Updating account details {}", id);
        AccountDetails existing = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account details not found: " + id));
        AccountDetailsDto previous = accountDetailsMapper.toDto(existing);
        AuditContextHolder.setPreviousState(previous);
        existing.setAccountId(dto.getAccountId());
        if (dto.getProfileId() != null && !dto.getProfileId().equals(existing.getProfile().getId())) {
            existing.setProfile(getProfile(dto.getProfileId()));
        }
        AccountDetails saved = accountDetailsRepository.save(existing);
        return accountDetailsMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting account details {}", id);
        AccountDetails accountDetails = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account details not found: " + id));
        accountDetailsRepository.delete(accountDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDetailsDto getById(Long id) {
        log.debug("Fetching account details {}", id);
        AccountDetails accountDetails = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account details not found: " + id));
        return accountDetailsMapper.toDto(accountDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDetailsDto> getAll() {
        log.debug("Fetching all account details");
        return accountDetailsRepository.findAll().stream()
                .map(accountDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDetailsDto> getByProfileId(Long profileId) {
        log.debug("Fetching account details for profile {}", profileId);
        return accountDetailsRepository.findByProfile_Id(profileId).stream()
                .map(accountDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    private Profile getProfile(Long profileId) {
        if (profileId == null) {
            throw new IllegalArgumentException("profileId is required to link account details");
        }
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + profileId));
    }
}

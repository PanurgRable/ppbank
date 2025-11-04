package com.bank.profile.service.impl;

import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entity.AccountDetails;
import com.bank.profile.entity.Profile;
import com.bank.profile.mapper.AccountDetailsMapper;
import com.bank.profile.repository.AccountDetailsRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.AccountDetailsService;
import com.bank.profile.service.AuditService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link AccountDetailsService}.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AccountDetailsServiceImpl implements AccountDetailsService {

    private final AccountDetailsRepository accountDetailsRepository;
    private final AccountDetailsMapper accountDetailsMapper;
    private final ProfileRepository profileRepository;
    private final AuditService auditService;

    @Override
    public AccountDetailsDto create(AccountDetailsDto dto) {
        Profile profile = getProfile(dto.getProfileId());
        AccountDetails accountDetails = accountDetailsMapper.toEntity(dto);
        accountDetails.setProfile(profile);
        AccountDetails saved = accountDetailsRepository.save(accountDetails);
        AccountDetailsDto result = accountDetailsMapper.toDto(saved);
        auditService.logCreate("AccountDetails", result);
        return result;
    }

    @Override
    public AccountDetailsDto update(Long id, AccountDetailsDto dto) {
        AccountDetails existing = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account details not found: " + id));
        AccountDetailsDto previous = accountDetailsMapper.toDto(existing);
        existing.setAccountId(dto.getAccountId());
        if (dto.getProfileId() != null && !dto.getProfileId().equals(existing.getProfile().getId())) {
            existing.setProfile(getProfile(dto.getProfileId()));
        }
        AccountDetails saved = accountDetailsRepository.save(existing);
        AccountDetailsDto result = accountDetailsMapper.toDto(saved);
        auditService.logUpdate("AccountDetails", previous, result);
        return result;
    }

    @Override
    public void delete(Long id) {
        AccountDetails accountDetails = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account details not found: " + id));
        accountDetailsRepository.delete(accountDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDetailsDto getById(Long id) {
        AccountDetails accountDetails = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account details not found: " + id));
        return accountDetailsMapper.toDto(accountDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDetailsDto> getAll() {
        return accountDetailsRepository.findAll().stream()
                .map(accountDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDetailsDto> getByProfileId(Long profileId) {
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

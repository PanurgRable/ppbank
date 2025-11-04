package com.bank.profile.service.impl;

import com.bank.profile.audit.AuditContextHolder;
import com.bank.profile.audit.Auditable;
import com.bank.profile.audit.OperationType;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.Profile;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ProfileService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    @Auditable(entity = "Profile", operation = OperationType.CREATE)
    public ProfileDto create(ProfileDto dto) {
        log.info("Creating profile for phone number {}", dto.getPhoneNumber());
        Profile profile = profileMapper.toEntity(dto);
        Profile saved = profileRepository.save(profile);
        return profileMapper.toDto(saved);
    }

    @Override
    @Auditable(entity = "Profile", operation = OperationType.UPDATE)
    public ProfileDto update(Long id, ProfileDto dto) {
        log.info("Updating profile {}", id);
        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + id));
        ProfileDto previousState = profileMapper.toDto(existing);
        AuditContextHolder.setPreviousState(previousState);
        Profile profile = profileMapper.toEntity(dto);
        profile.setId(id);
        Profile saved = profileRepository.save(profile);
        return profileMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting profile {}", id);
        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + id));
        profileRepository.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDto getById(Long id) {
        log.debug("Fetching profile {}", id);
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + id));
        return profileMapper.toDto(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDto> getAll() {
        log.debug("Fetching all profiles");
        return profileRepository.findAll().stream()
                .map(profileMapper::toDto)
                .collect(Collectors.toList());
    }
}

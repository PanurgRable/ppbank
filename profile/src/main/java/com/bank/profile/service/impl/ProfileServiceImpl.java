package com.bank.profile.service.impl;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.Profile;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.AuditService;
import com.bank.profile.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ProfileService}.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final AuditService auditService;

    @Override
    public ProfileDto create(ProfileDto dto) {
        Profile profile = profileMapper.toEntity(dto);
        Profile saved = profileRepository.save(profile);
        ProfileDto result = profileMapper.toDto(saved);
        auditService.logCreate("Profile", result);
        return result;
    }

    @Override
    public ProfileDto update(Long id, ProfileDto dto) {
        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + id));
        ProfileDto previousState = profileMapper.toDto(existing);
        Profile profile = profileMapper.toEntity(dto);
        profile.setId(id);
        Profile saved = profileRepository.save(profile);
        ProfileDto result = profileMapper.toDto(saved);
        auditService.logUpdate("Profile", previousState, result);
        return result;
    }

    @Override
    public void delete(Long id) {
        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + id));
        profileRepository.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDto getById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + id));
        return profileMapper.toDto(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDto> getAll() {
        return profileRepository.findAll().stream()
                .map(profileMapper::toDto)
                .collect(Collectors.toList());
    }
}

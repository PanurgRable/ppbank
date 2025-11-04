package com.bank.profile.service.impl;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.Registration;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.service.AuditService;
import com.bank.profile.service.RegistrationService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link RegistrationService}.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final AuditService auditService;

    @Override
    public RegistrationDto create(RegistrationDto dto) {
        Registration registration = registrationMapper.toEntity(dto);
        Registration saved = registrationRepository.save(registration);
        RegistrationDto result = registrationMapper.toDto(saved);
        auditService.logCreate("Registration", result);
        return result;
    }

    @Override
    public RegistrationDto update(Long id, RegistrationDto dto) {
        Registration existing = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found: " + id));
        RegistrationDto previous = registrationMapper.toDto(existing);
        Registration registration = registrationMapper.toEntity(dto);
        registration.setId(id);
        Registration saved = registrationRepository.save(registration);
        RegistrationDto result = registrationMapper.toDto(saved);
        auditService.logUpdate("Registration", previous, result);
        return result;
    }

    @Override
    public void delete(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found: " + id));
        registrationRepository.delete(registration);
    }

    @Override
    @Transactional(readOnly = true)
    public RegistrationDto getById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found: " + id));
        return registrationMapper.toDto(registration);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistrationDto> getAll() {
        return registrationRepository.findAll().stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }
}

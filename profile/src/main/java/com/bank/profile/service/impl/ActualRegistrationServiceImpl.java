package com.bank.profile.service.impl;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.mapper.ActualRegistrationMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.service.ActualRegistrationService;
import com.bank.profile.service.AuditService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ActualRegistrationService}.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ActualRegistrationServiceImpl implements ActualRegistrationService {

    private final ActualRegistrationRepository actualRegistrationRepository;
    private final ActualRegistrationMapper actualRegistrationMapper;
    private final AuditService auditService;

    @Override
    public ActualRegistrationDto create(ActualRegistrationDto dto) {
        ActualRegistration actualRegistration = actualRegistrationMapper.toEntity(dto);
        ActualRegistration saved = actualRegistrationRepository.save(actualRegistration);
        ActualRegistrationDto result = actualRegistrationMapper.toDto(saved);
        auditService.logCreate("ActualRegistration", result);
        return result;
    }

    @Override
    public ActualRegistrationDto update(Long id, ActualRegistrationDto dto) {
        ActualRegistration existing = actualRegistrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actual registration not found: " + id));
        ActualRegistrationDto previous = actualRegistrationMapper.toDto(existing);
        ActualRegistration actualRegistration = actualRegistrationMapper.toEntity(dto);
        actualRegistration.setId(id);
        ActualRegistration saved = actualRegistrationRepository.save(actualRegistration);
        ActualRegistrationDto result = actualRegistrationMapper.toDto(saved);
        auditService.logUpdate("ActualRegistration", previous, result);
        return result;
    }

    @Override
    public void delete(Long id) {
        ActualRegistration actualRegistration = actualRegistrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actual registration not found: " + id));
        actualRegistrationRepository.delete(actualRegistration);
    }

    @Override
    @Transactional(readOnly = true)
    public ActualRegistrationDto getById(Long id) {
        ActualRegistration actualRegistration = actualRegistrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actual registration not found: " + id));
        return actualRegistrationMapper.toDto(actualRegistration);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActualRegistrationDto> getAll() {
        return actualRegistrationRepository.findAll().stream()
                .map(actualRegistrationMapper::toDto)
                .collect(Collectors.toList());
    }
}

package com.bank.profile.service.impl;

import com.bank.profile.audit.AuditContextHolder;
import com.bank.profile.audit.Auditable;
import com.bank.profile.audit.OperationType;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.Registration;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.service.RegistrationService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link RegistrationService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;

    @Override
    @Auditable(entity = "Registration", operation = OperationType.CREATE)
    public RegistrationDto create(RegistrationDto dto) {
        log.info("Creating registration for city {}", dto.getCity());
        Registration registration = registrationMapper.toEntity(dto);
        Registration saved = registrationRepository.save(registration);
        return registrationMapper.toDto(saved);
    }

    @Override
    @Auditable(entity = "Registration", operation = OperationType.UPDATE)
    public RegistrationDto update(Long id, RegistrationDto dto) {
        log.info("Updating registration {}", id);
        Registration existing = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found: " + id));
        RegistrationDto previous = registrationMapper.toDto(existing);
        AuditContextHolder.setPreviousState(previous);
        Registration registration = registrationMapper.toEntity(dto);
        registration.setId(id);
        Registration saved = registrationRepository.save(registration);
        return registrationMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting registration {}", id);
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found: " + id));
        registrationRepository.delete(registration);
    }

    @Override
    @Transactional(readOnly = true)
    public RegistrationDto getById(Long id) {
        log.debug("Fetching registration {}", id);
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found: " + id));
        return registrationMapper.toDto(registration);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistrationDto> getAll() {
        log.debug("Fetching all registrations");
        return registrationRepository.findAll().stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }
}

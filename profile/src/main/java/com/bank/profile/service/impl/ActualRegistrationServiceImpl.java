package com.bank.profile.service.impl;

import com.bank.profile.audit.AuditContextHolder;
import com.bank.profile.audit.Auditable;
import com.bank.profile.audit.OperationType;
import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.mapper.ActualRegistrationMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.service.ActualRegistrationService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ActualRegistrationService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ActualRegistrationServiceImpl implements ActualRegistrationService {

    private final ActualRegistrationRepository actualRegistrationRepository;
    private final ActualRegistrationMapper actualRegistrationMapper;

    @Override
    @Auditable(entity = "ActualRegistration", operation = OperationType.CREATE)
    public ActualRegistrationDto create(ActualRegistrationDto dto) {
        log.info("Creating actual registration for city {}", dto.getCity());
        ActualRegistration actualRegistration = actualRegistrationMapper.toEntity(dto);
        ActualRegistration saved = actualRegistrationRepository.save(actualRegistration);
        return actualRegistrationMapper.toDto(saved);
    }

    @Override
    @Auditable(entity = "ActualRegistration", operation = OperationType.UPDATE)
    public ActualRegistrationDto update(Long id, ActualRegistrationDto dto) {
        log.info("Updating actual registration {}", id);
        ActualRegistration existing = actualRegistrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actual registration not found: " + id));
        ActualRegistrationDto previous = actualRegistrationMapper.toDto(existing);
        AuditContextHolder.setPreviousState(previous);
        ActualRegistration actualRegistration = actualRegistrationMapper.toEntity(dto);
        actualRegistration.setId(id);
        ActualRegistration saved = actualRegistrationRepository.save(actualRegistration);
        return actualRegistrationMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting actual registration {}", id);
        ActualRegistration actualRegistration = actualRegistrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actual registration not found: " + id));
        actualRegistrationRepository.delete(actualRegistration);
    }

    @Override
    @Transactional(readOnly = true)
    public ActualRegistrationDto getById(Long id) {
        log.debug("Fetching actual registration {}", id);
        ActualRegistration actualRegistration = actualRegistrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actual registration not found: " + id));
        return actualRegistrationMapper.toDto(actualRegistration);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActualRegistrationDto> getAll() {
        log.debug("Fetching all actual registrations");
        return actualRegistrationRepository.findAll().stream()
                .map(actualRegistrationMapper::toDto)
                .collect(Collectors.toList());
    }
}

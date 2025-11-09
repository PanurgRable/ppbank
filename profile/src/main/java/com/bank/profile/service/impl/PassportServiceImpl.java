package com.bank.profile.service.impl;

import com.bank.profile.audit.AuditContextHolder;
import com.bank.profile.audit.Auditable;
import com.bank.profile.audit.OperationType;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.Passport;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.service.PassportService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link PassportService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;

    @Override
    @Auditable(entity = "Passport", operation = OperationType.CREATE)
    public PassportDto create(PassportDto dto) {
        log.info("Creating passport {} {}", dto.getSeries(), dto.getNumber());
        Passport passport = passportMapper.toEntity(dto);
        Passport saved = passportRepository.save(passport);
        return passportMapper.toDto(saved);
    }

    @Override
    @Auditable(entity = "Passport", operation = OperationType.UPDATE)
    public PassportDto update(Long id, PassportDto dto) {
        log.info("Updating passport {}", id);
        Passport existing = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport not found: " + id));
        PassportDto previous = passportMapper.toDto(existing);
        AuditContextHolder.setPreviousState(previous);
        Passport passport = passportMapper.toEntity(dto);
        passport.setId(id);
        Passport saved = passportRepository.save(passport);
        return passportMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting passport {}", id);
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport not found: " + id));
        passportRepository.delete(passport);
    }

    @Override
    @Transactional(readOnly = true)
    public PassportDto getById(Long id) {
        log.debug("Fetching passport {}", id);
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport not found: " + id));
        return passportMapper.toDto(passport);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassportDto> getAll() {
        log.debug("Fetching all passports");
        return passportRepository.findAll().stream()
                .map(passportMapper::toDto)
                .collect(Collectors.toList());
    }
}

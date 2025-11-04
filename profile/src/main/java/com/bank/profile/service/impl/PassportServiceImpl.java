package com.bank.profile.service.impl;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.Passport;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.service.AuditService;
import com.bank.profile.service.PassportService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link PassportService}.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    private final AuditService auditService;

    @Override
    public PassportDto create(PassportDto dto) {
        Passport passport = passportMapper.toEntity(dto);
        Passport saved = passportRepository.save(passport);
        PassportDto result = passportMapper.toDto(saved);
        auditService.logCreate("Passport", result);
        return result;
    }

    @Override
    public PassportDto update(Long id, PassportDto dto) {
        Passport existing = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport not found: " + id));
        PassportDto previous = passportMapper.toDto(existing);
        Passport passport = passportMapper.toEntity(dto);
        passport.setId(id);
        Passport saved = passportRepository.save(passport);
        PassportDto result = passportMapper.toDto(saved);
        auditService.logUpdate("Passport", previous, result);
        return result;
    }

    @Override
    public void delete(Long id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport not found: " + id));
        passportRepository.delete(passport);
    }

    @Override
    @Transactional(readOnly = true)
    public PassportDto getById(Long id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport not found: " + id));
        return passportMapper.toDto(passport);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassportDto> getAll() {
        return passportRepository.findAll().stream()
                .map(passportMapper::toDto)
                .collect(Collectors.toList());
    }
}

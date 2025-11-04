package com.bank.profile.service;

import com.bank.profile.dto.PassportDto;
import java.util.List;

/**
 * Service for managing passport entities.
 */
public interface PassportService {

    PassportDto create(PassportDto dto);

    PassportDto update(Long id, PassportDto dto);

    void delete(Long id);

    PassportDto getById(Long id);

    List<PassportDto> getAll();
}

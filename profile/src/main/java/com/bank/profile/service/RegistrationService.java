package com.bank.profile.service;

import com.bank.profile.dto.RegistrationDto;
import java.util.List;

/**
 * Service for managing registration entities.
 */
public interface RegistrationService {

    RegistrationDto create(RegistrationDto dto);

    RegistrationDto update(Long id, RegistrationDto dto);

    void delete(Long id);

    RegistrationDto getById(Long id);

    List<RegistrationDto> getAll();
}

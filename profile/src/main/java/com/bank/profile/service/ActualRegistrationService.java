package com.bank.profile.service;

import com.bank.profile.dto.ActualRegistrationDto;
import java.util.List;

/**
 * Service for managing actual registration entities.
 */
public interface ActualRegistrationService {

    ActualRegistrationDto create(ActualRegistrationDto dto);

    ActualRegistrationDto update(Long id, ActualRegistrationDto dto);

    void delete(Long id);

    ActualRegistrationDto getById(Long id);

    List<ActualRegistrationDto> getAll();
}

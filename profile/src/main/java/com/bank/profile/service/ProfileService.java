package com.bank.profile.service;

import com.bank.profile.dto.ProfileDto;
import java.util.List;

/**
 * Service for managing profile entities.
 */
public interface ProfileService {

    ProfileDto create(ProfileDto dto);

    ProfileDto update(Long id, ProfileDto dto);

    void delete(Long id);

    ProfileDto getById(Long id);

    List<ProfileDto> getAll();
}

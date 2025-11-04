package com.bank.profile.mapper;

import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entity.AccountDetails;
import com.bank.profile.entity.Profile;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * MapStruct mapper for {@link AccountDetails} entities.
 */
@Mapper(componentModel = "spring")
public interface AccountDetailsMapper {

    @Mapping(target = "profileId", source = "profile.id")
    AccountDetailsDto toDto(AccountDetails entity);

    @InheritInverseConfiguration
    @Mapping(target = "profile", ignore = true)
    AccountDetails toEntity(AccountDetailsDto dto);

    @AfterMapping
    default void linkProfile(AccountDetailsDto dto, @MappingTarget AccountDetails entity) {
        if (dto != null && dto.getProfileId() != null) {
            entity.setProfile(Profile.builder().id(dto.getProfileId()).build());
        }
    }
}

package com.bank.profile.mapper;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.Profile;
import java.util.HashSet;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * MapStruct mapper for {@link Profile} entities.
 */
@Mapper(componentModel = "spring", uses = {
        PassportMapper.class,
        ActualRegistrationMapper.class,
        AccountDetailsMapper.class
})
public interface ProfileMapper {

    ProfileDto toDto(Profile entity);

    Profile toEntity(ProfileDto dto);

    @AfterMapping
    default void linkAccountDetails(@MappingTarget Profile entity) {
        if (entity.getAccountDetails() == null) {
            entity.setAccountDetails(new HashSet<>());
            return;
        }
        entity.getAccountDetails().forEach(details -> details.setProfile(entity));
    }
}

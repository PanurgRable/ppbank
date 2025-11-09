package com.bank.profile.mapper;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.Registration;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * MapStruct mapper for {@link Registration} entities.
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RegistrationMapper {

    RegistrationDto toDto(Registration entity);

    Registration toEntity(RegistrationDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(RegistrationDto dto, @MappingTarget Registration entity);
}

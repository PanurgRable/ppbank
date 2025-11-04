package com.bank.profile.mapper;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistration;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * MapStruct mapper for {@link ActualRegistration} entities.
 */
@Mapper(componentModel = "spring")
public interface ActualRegistrationMapper {

    ActualRegistrationDto toDto(ActualRegistration entity);

    ActualRegistration toEntity(ActualRegistrationDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ActualRegistrationDto dto, @MappingTarget ActualRegistration entity);
}

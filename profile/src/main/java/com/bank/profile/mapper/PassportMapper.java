package com.bank.profile.mapper;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.Passport;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * MapStruct mapper for {@link Passport} entities.
 */
@Mapper(componentModel = "spring", uses = RegistrationMapper.class, builder = @Builder(disableBuilder = true))
public interface PassportMapper {

    PassportDto toDto(Passport entity);

    Passport toEntity(PassportDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PassportDto dto, @MappingTarget Passport entity);

    @AfterMapping
    default void ensureRegistration(PassportDto dto, @MappingTarget Passport entity) {
        if (dto == null) {
            return;
        }
        if (dto.getRegistration() == null) {
            entity.setRegistration(null);
        }
    }
}

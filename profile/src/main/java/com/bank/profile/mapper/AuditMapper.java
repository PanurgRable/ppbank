package com.bank.profile.mapper;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.Audit;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for {@link Audit} entities.
 */
@Mapper(componentModel = "spring")
public interface AuditMapper {

    AuditDto toDto(Audit entity);

    Audit toEntity(AuditDto dto);
}

package com.bank.profile.mapper;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.Audit;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for {@link Audit} entities.
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AuditMapper {

    AuditDto toDto(Audit entity);

    Audit toEntity(AuditDto dto);
}

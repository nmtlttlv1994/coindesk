package com.homework.coindesk.mapper;

import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.entity.CurrencyEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CurrencyMapper {
    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);
    @Named("toDto")
    CurrencyDto toDto(final CurrencyEntity entity);

    @Named("toEntity")
    CurrencyEntity toEntity(final CurrencyDto dto);

    @Named("toDtos")
    @IterableMapping(qualifiedByName = "toDto")
    List<CurrencyDto> toDto(final List<CurrencyEntity> entities);

    @Named("toEntities")
    @IterableMapping(qualifiedByName = "toEntity")
    List<CurrencyEntity> toEntity(final List<CurrencyDto> dtos);
}

package com.homework.coindesk.mapper;

import com.homework.coindesk.CurrencyProjection;
import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.entity.CurrencyEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
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

//    @Named("toProjection")
//    CurrencyEntity toProjection(final CurrencyProjection entity);
//
//    @Named("toProjections")
//    @IterableMapping(qualifiedByName = "toProjection")
//    List<CurrencyEntity> toProjections(final List<CurrencyProjection> entity);
}

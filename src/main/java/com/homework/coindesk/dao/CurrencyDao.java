package com.homework.coindesk.dao;

import com.homework.coindesk.dto.CurrencyDto;

import java.util.List;

public interface CurrencyDao {

    CurrencyDto findByCode(String code);

    List<CurrencyDto> upsert(List<CurrencyDto> dtos);

    CurrencyDto upsertOne(CurrencyDto dto);

    List<CurrencyDto> getAllCurrency();
}

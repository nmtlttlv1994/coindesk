package com.homework.coindesk.dao;

import com.homework.coindesk.CurrencyProjection;
import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.filter.CurrencyFilter;

import java.util.List;

public interface CurrencyDao {

    CurrencyProjection findByCode(String code, String language);
    CurrencyDto findByCode(String code);

    List<CurrencyDto> upsert(List<CurrencyDto> dtos);

    CurrencyDto upsertOne(CurrencyDto dto);

    List<CurrencyDto> getAllCurrency();
}

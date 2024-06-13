package com.homework.coindesk.service;


import com.homework.coindesk.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDto> syncData(boolean isUpdate);

    CurrencyDto getByCode(String code);

    CurrencyDto upsertOne(CurrencyDto dto);

    Boolean deleteOne(String code);

    List<CurrencyDto> getAllCurrency();

}

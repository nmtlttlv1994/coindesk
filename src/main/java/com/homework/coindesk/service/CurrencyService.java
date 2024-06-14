package com.homework.coindesk.service;


import com.homework.coindesk.CurrencyProjection;
import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.filter.CurrencyFilter;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDto> syncData(boolean isUpdate);

    CurrencyProjection getByCode(String code, String language);

    CurrencyDto upsertOne(CurrencyFilter filter);

    Boolean deleteOne(String code);

    List<CurrencyDto> getAllCurrency();

}

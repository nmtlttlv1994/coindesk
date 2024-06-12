package com.homework.coindesk.dao;

import com.homework.coindesk.dto.CurrencyDto;

public interface CurrencyDao {

    CurrencyDto findByCode(String code);
} 

package com.homework.coindesk.service.impl;

import com.homework.coindesk.dto.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.homework.coindesk.dao.CurrencyDao;
import com.homework.coindesk.service.CurrencyService;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${server.coin-desk-url}")
    private String coinDeskUrl;

    @Autowired
    private CurrencyDao currencyDao;

    @Override
    public List<CurrencyDto> syncData(boolean isUpdate) {
        return null;
    }
}

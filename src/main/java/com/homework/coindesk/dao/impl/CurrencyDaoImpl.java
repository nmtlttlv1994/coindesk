package com.homework.coindesk.dao.impl;

import com.homework.coindesk.dao.CurrencyDao;
import com.homework.coindesk.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homework.coindesk.repository.CurrencyRepository;

@Component
@RequiredArgsConstructor
public class CurrencyDaoImpl implements CurrencyDao {
    private final CurrencyRepository repository;

    @Override
    public CurrencyDto findByCode(String code) {
        return null;
    }
}

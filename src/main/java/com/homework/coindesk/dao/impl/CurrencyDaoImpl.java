package com.homework.coindesk.dao.impl;

import com.homework.coindesk.dao.CurrencyDao;
import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.mapper.CurrencyMapper;
import com.homework.coindesk.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyDaoImpl implements CurrencyDao {

    @Autowired
    private CurrencyRepository repository;

    @Autowired
    private CurrencyMapper mapper;

    @Override
    public CurrencyDto findByCode(String code) {
        return mapper.toDto(repository.findByCodeAndActive(code, Boolean.TRUE).orElse(null));
    }

    @Override
    public List<CurrencyDto> upsert(List<CurrencyDto> dtos) {
        return mapper.toDto(repository.saveAllAndFlush(mapper.toEntity(dtos)));
    }

    @Override
    public CurrencyDto upsertOne(CurrencyDto dto) {
        return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto)));
    }

    @Override
    public List<CurrencyDto> getAllCurrency() {
        return mapper.toDto(repository.findAllByActive(Boolean.TRUE));
    }
}

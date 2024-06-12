package com.homework.coindesk.service.impl;

import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.response.BpiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.homework.coindesk.dao.CurrencyDao;
import com.homework.coindesk.service.CurrencyService;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${server.coin-desk-url}")
    private String url;

    private final CurrencyDao currencyDao;

    private final RestTemplate restTemplate;
    @Override
    public List<CurrencyDto> syncData(boolean isUpdate) {
        BpiResponse bpiResponse = restTemplate.getForObject(url, BpiResponse.class);
        Map<String, CurrencyDto> map = new HashMap<>();
        if (isUpdate) {

        } else {

        }
        return null;
    }
}

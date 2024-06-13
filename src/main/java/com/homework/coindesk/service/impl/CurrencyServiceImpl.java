package com.homework.coindesk.service.impl;

import com.homework.coindesk.dao.CurrencyDao;
import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.response.BpiResponse;
import com.homework.coindesk.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${server.coin-desk-url}")
    private String url;

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional
    public List<CurrencyDto> syncData(boolean isUpdate) {
        try {
            BpiResponse bpiResponse = restTemplate.getForObject(url, BpiResponse.class);
            if (!ObjectUtils.isEmpty(bpiResponse) && !CollectionUtils.isEmpty(bpiResponse.getBpi()))
                if (isUpdate) {
                    return currencyDao.upsert(new ArrayList<>(bpiResponse.getBpi().values()));
                }
            return new ArrayList<>(Objects.requireNonNull(bpiResponse).getBpi().values());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CurrencyDto getByCode(String code) {
        return currencyDao.findByCode(code);
    }

    @Override
    @Transactional
    public CurrencyDto upsertOne(CurrencyDto dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new RuntimeException("code_is_required");
        }
        return currencyDao.upsertOne(dto);
    }

    @Override
    public Boolean deleteOne(String code) {
        CurrencyDto dto = currencyDao.findByCode(code);
        if (!ObjectUtils.isEmpty(dto)) {
            dto.setActive(Boolean.FALSE);
            currencyDao.upsertOne(dto);
            return true;
        }
        throw new RuntimeException("data_not_found");
    }

    @Override
    public List<CurrencyDto> getAllCurrency() {
        return currencyDao.getAllCurrency();
    }
}

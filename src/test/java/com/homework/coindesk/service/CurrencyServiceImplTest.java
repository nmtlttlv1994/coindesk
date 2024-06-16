package com.homework.coindesk.service;

import com.homework.coindesk.CurrencyProjection;
import com.homework.coindesk.dao.CurrencyDao;
import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.filter.CurrencyFilter;
import com.homework.coindesk.service.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Mock
    private CurrencyDao currencyDao;

    @Value("${server.coin-desk-url}")
    private String url;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(currencyService, "url", url);
    }

    @Test
    public void testUpsertOne() {
        CurrencyFilter mockDto = new CurrencyFilter();
        CurrencyDto dto = new CurrencyDto();
        dto.setCode("USD");
        dto.setDescription("Description");
        mockDto.setCriteria(dto);

        when(currencyDao.upsertOne(dto)).thenReturn(dto);

        CurrencyDto result = currencyService.upsertOne(mockDto);

        assertNotNull(result);
        assertEquals(mockDto.getCriteria(), result);
    }

    @Test
    public void testDeleteOne_Success() {
        String code = "USD";
        CurrencyFilter mockDto = new CurrencyFilter();
        CurrencyDto dto = new CurrencyDto();
        dto.setCode("USD");
        dto.setDescription("Description");
        mockDto.setCriteria(dto);

        when(currencyDao.upsertOne(dto)).thenReturn(dto);

        when(currencyDao.findByCode(code)).thenReturn(dto);
        when(currencyDao.upsertOne(dto)).thenReturn(dto);

        Boolean result = currencyService.deleteOne(code);

        assertTrue(result);
        verify(currencyDao, times(1)).findByCode(code);
        verify(currencyDao, times(1)).upsertOne(dto);
    }

    @Test
    public void testDeleteOne_NotFound() {
        String code = "USD";

        when(currencyDao.findByCode(code)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            currencyService.deleteOne(code);
        });

        assertEquals("data_not_found", exception.getMessage());
        verify(currencyDao, times(1)).findByCode(code);
    }

    @Test
    public void testGetAllCurrency() {
        List<CurrencyDto> mockList = Collections.emptyList();

        when(currencyDao.getAllCurrency()).thenReturn(mockList);

        List<CurrencyDto> result = currencyService.getAllCurrency();

        assertNotNull(result);
        assertEquals(mockList, result);
    }
}
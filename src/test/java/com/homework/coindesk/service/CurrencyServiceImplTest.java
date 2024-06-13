package com.homework.coindesk.service;

import com.homework.coindesk.dao.CurrencyDao;
import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.service.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceImplTest {

    @Mock
    private CurrencyDao currencyDao;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Test
    public void testGetByCode() {
        // Mock data
        CurrencyDto mockCurrency = new CurrencyDto();
        mockCurrency.setCode("USD");
        mockCurrency.setDescription("Description");
        when(currencyDao.findByCode("USD")).thenReturn(mockCurrency);

        // Call the method under test
        CurrencyDto result = currencyService.getByCode("USD");

        // Assertions
        assertNotNull(result);
        assertEquals("USD", result.getCode());
        assertEquals("Description", result.getDescription());

        // Verify interactions
        verify(currencyDao, times(1)).findByCode("USD");
    }

    @Test
    public void testUpsertOne() {
        // Mock data
        CurrencyDto dto = new CurrencyDto();
        dto.setCode("USD");
        dto.setDescription("Description");

        // Mock behavior of currencyDao.upsertOne(dto)
        when(currencyDao.upsertOne(dto)).thenReturn(dto);

        // Call the method under test
        CurrencyDto result = currencyService.upsertOne(dto);

        // Assertions
        assertNotNull(result);
        assertEquals("USD", result.getCode());
        assertEquals("Description", result.getDescription());

        // Verify interactions
        verify(currencyDao, times(1)).upsertOne(dto);
    }

    @Test
    public void testDeleteOne_ExistingData() {
        // Mock data
        CurrencyDto existingDto = new CurrencyDto();
        existingDto.setCode("USD");
        existingDto.setDescription("Description");

        when(currencyDao.findByCode("USD")).thenReturn(existingDto);

        // Call the method under test
        boolean result = currencyService.deleteOne("USD");

        // Assertions
        assertTrue(result);

        // Verify interactions
        verify(currencyDao, times(1)).findByCode("USD");
        verify(currencyDao, times(1)).upsertOne(existingDto);
        assertFalse(existingDto.getActive()); // Verify that isActive is set to false
    }

    @Test
    public void testDeleteOne_NonExistingData() {
        // Mock behavior of currencyDao.findByCode("XXX")
        when(currencyDao.findByCode("XXX")).thenReturn(null);

        // Call the method under test and expect an exception
        assertThrows(RuntimeException.class, () -> currencyService.deleteOne("XXX"));

        // Verify interactions
        verify(currencyDao, times(1)).findByCode("XXX");
        verify(currencyDao, never()).upsertOne(any());
    }

    @Test
    public void testGetAllCurrency() {
        // Mock data
        CurrencyDto usd = new CurrencyDto();
        usd.setCode("USD");
        usd.setDescription("Test des");
        CurrencyDto eur = new CurrencyDto();
        eur.setCode("EUR");
        eur.setDescription("Test des");

        List<CurrencyDto> mockCurrencyList = Arrays.asList(usd, eur);
        when(currencyDao.getAllCurrency()).thenReturn(mockCurrencyList);

        // Call the method under test
        List<CurrencyDto> result = currencyService.getAllCurrency();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("USD", result.get(0).getCode());
        assertEquals("Test des", result.get(0).getDescription());
        assertEquals("EUR", result.get(1).getCode());
        assertEquals("Test des", result.get(1).getDescription());

        // Verify interactions
        verify(currencyDao, times(1)).getAllCurrency();
    }
}
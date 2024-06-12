package com.homework.coindesk.controller;

import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.service.CurrencyService;
import com.homework.coindesk.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController("coin-desk")
@RequestMapping("coin-desk")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;
    @GetMapping("/{isUpdate}")
    public ApiResponse<List<CurrencyDto>> synchronizedData(@PathVariable boolean isUpdate) {
        List<CurrencyDto> syncData = currencyService.syncData(isUpdate);
        return ApiResponse.ok(syncData);
    }
}

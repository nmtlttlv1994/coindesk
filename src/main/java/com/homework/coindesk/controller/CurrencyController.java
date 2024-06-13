package com.homework.coindesk.controller;

import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.service.CurrencyService;
import com.homework.coindesk.util.ApiResponse;
import com.homework.coindesk.util.Constraint;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController(Constraint.CURRENCY)
@RequestMapping(Constraint.CURRENCY)
@Api(tags = {Constraint.CURRENCY})
@Log4j2
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/sync-data/{isUpdate}")
    public ApiResponse<List<CurrencyDto>> synchronizedData(@PathVariable boolean isUpdate) {
        List<CurrencyDto> syncData = currencyService.syncData(isUpdate);
        return ApiResponse.ok(syncData);
    }
    @GetMapping("/all")
    public ApiResponse<List<CurrencyDto>> getAll() {
        return ApiResponse.ok(currencyService.getAllCurrency());
    }
    @GetMapping("/{code}")
    public ApiResponse<CurrencyDto> getByCode(@PathVariable String code) {
        return ApiResponse.ok(currencyService.getByCode(code));
    }
    @PostMapping
    public ApiResponse<CurrencyDto> upsertOne(@RequestBody CurrencyDto dto) {

        return ApiResponse.ok(currencyService.upsertOne(dto));
    }

    @DeleteMapping("/{code}")
    public ApiResponse<Boolean> deleteOne(@PathVariable String code) {
        return ApiResponse.ok(currencyService.deleteOne(code));
    }
}

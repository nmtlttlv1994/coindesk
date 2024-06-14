package com.homework.coindesk.controller;

import com.homework.coindesk.CurrencyProjection;
import com.homework.coindesk.dto.CurrencyDto;
import com.homework.coindesk.filter.CurrencyFilter;
import com.homework.coindesk.service.CurrencyService;
import com.homework.coindesk.util.Constraint;
import com.homework.coindesk.util.GeneralApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;
import java.util.List;

@RestController(Constraint.CURRENCY)
@RequestMapping(Constraint.CURRENCY)
@Log4j2
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Operation(summary = "Sync data from external system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "found data from external system",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Currency.class))}
            ),
            @ApiResponse(responseCode = "204",
                    description = "not found data from external system",
                    content = @Content
            )
    })
    @GetMapping("/sync-data/{isUpdate}")
    public GeneralApiResponse<List<CurrencyDto>> synchronizedData(@PathVariable boolean isUpdate) {
        List<CurrencyDto> syncData = currencyService.syncData(isUpdate);
        return GeneralApiResponse.ok(syncData);
    }

    @GetMapping("/all")
    public GeneralApiResponse<List<CurrencyDto>> getAll() {
        return GeneralApiResponse.ok(currencyService.getAllCurrency());
    }

    @GetMapping("/{code}/{language}")
    public GeneralApiResponse<CurrencyProjection> getByCode(@PathVariable String code, @PathVariable String language) {
        return GeneralApiResponse.ok(currencyService.getByCode(code, language));
    }

    @PostMapping
    public GeneralApiResponse<CurrencyDto> upsertOne(@RequestBody CurrencyFilter filter) {

        return GeneralApiResponse.ok(currencyService.upsertOne(filter));
    }

    @DeleteMapping("/{code}")
    public GeneralApiResponse<Boolean> deleteOne(@PathVariable String code) {
        return GeneralApiResponse.ok(currencyService.deleteOne(code));
    }
}

package com.homework.coindesk.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.homework.coindesk.dto.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BpiResponse {
    @Data
    public static class TimeDTO{
        private String updated;
        private String updatedISO;
        private String updateduk;
    }

    @Data
    public static class CurrencyDTO {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        @JsonProperty("rate_float")
        private Float rateFloat;
    }

    @Data
    public static class BpiDTO {

        @JsonProperty("USD")
        private CurrencyDTO usd;
        @JsonProperty("GBP")
        private CurrencyDTO gbp;
        @JsonProperty("EUR")
        private CurrencyDTO eur;
    }

    private TimeDTO time;
    private String disclaimer;
    private String chartName;
    private BpiDTO bpi;
}

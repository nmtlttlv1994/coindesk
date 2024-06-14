package com.homework.coindesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface CurrencyProjection {

    String getCode();

    String getSymbol();

    String getRate();

    String getDescription();

    Float getRateFloat();

    String getName();

    String getLanguageCode();

}

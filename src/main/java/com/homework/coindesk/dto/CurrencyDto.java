package com.homework.coindesk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private Float rateFloat;
}

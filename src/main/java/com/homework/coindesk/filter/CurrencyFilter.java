package com.homework.coindesk.filter;

import com.homework.coindesk.dto.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyFilter extends ApiFilter<CurrencyDto> {
    private String languageCode;
    private boolean isUpdate;
}

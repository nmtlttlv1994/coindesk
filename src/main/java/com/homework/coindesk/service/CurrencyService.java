package com.homework.coindesk.service;


import com.homework.coindesk.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDto> syncData(boolean isUpdate);
}

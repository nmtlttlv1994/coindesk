package com.homework.coindesk.config;

import com.homework.coindesk.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@Component
public class SchedulerConfig {

    @Autowired
    private CurrencyService currencyService;


    // schedule each 5 mins: 0 */5 * * * ?
    @Scheduled(cron = "${scheduler.cron-trigger}")
    public void schedule() {
        log.info("Start synchronize data at: " + LocalDateTime.now());
        currencyService.syncData(true);
        log.info("sync completed at: " + LocalDateTime.now());
    }
}

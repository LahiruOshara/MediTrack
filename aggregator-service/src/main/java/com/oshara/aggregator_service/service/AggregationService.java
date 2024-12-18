package com.oshara.aggregator_service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AggregationService {

    @Scheduled(fixedRate = 60000)  // Runs every minute
    public void aggregateData() {
        // Implement logic to collect and aggregate data from other services
        System.out.println("Running data aggregation job...");
    }
}

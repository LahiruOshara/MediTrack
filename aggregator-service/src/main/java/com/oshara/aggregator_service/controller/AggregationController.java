package com.oshara.aggregator_service.controller;

import com.oshara.aggregator_service.service.AggregationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/aggregation")
public class AggregationController {

    private AggregationService aggregationService;

    @GetMapping("/run")
    public String runAggregation() {
        aggregationService.aggregateData();
        return "Aggregation job executed.";
    }
}
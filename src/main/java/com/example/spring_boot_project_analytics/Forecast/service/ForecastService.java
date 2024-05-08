package com.example.spring_boot_project_analytics.Forecast.service;

import reactor.core.publisher.Mono;

public interface ForecastService {
    Mono<Void> sendFileToFlaskAndGetForecastAsync(Long metadata);
}

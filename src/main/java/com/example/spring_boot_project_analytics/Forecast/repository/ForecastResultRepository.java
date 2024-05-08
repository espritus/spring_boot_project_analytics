package com.example.spring_boot_project_analytics.Forecast.repository;

import com.example.spring_boot_project_analytics.Forecast.entity.ForecastResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastResultRepository extends JpaRepository<ForecastResult, Long> {
}

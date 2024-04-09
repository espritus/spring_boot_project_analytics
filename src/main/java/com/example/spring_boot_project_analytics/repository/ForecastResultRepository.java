package com.example.spring_boot_project_analytics.repository;

import com.example.spring_boot_project_analytics.entity.ForecastResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastResultRepository extends JpaRepository<ForecastResult, Long> {
}

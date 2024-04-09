package com.example.spring_boot_project_analytics.Flask;

import com.example.spring_boot_project_analytics.entity.ForecastResult;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatus {
    private String state;
    private List<ForecastResult> result;
}

package com.example.spring_boot_project_analytics.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResultDto {
    private int productId;

    private double predictedSales;
    private double expenditure;
    private double balanceEnd;
    private double balanceStart;
    private double turnover;
    private double turnoverDiff;
    private int orders;
    private int arrival;
    private int backorder;
    private int transit;
    private LocalDate month;
    private String productCategory;
}

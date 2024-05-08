package com.example.spring_boot_project_analytics.Forecast.entity;

import com.example.spring_boot_project_analytics.TableDetails.entity.TableDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filtered_data")
public class ForecastResult {
    @Id
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate month;
    private String productCategory;

    @ManyToOne
    @JoinColumn(name = "metadataId")
    private TableDetails metadata;
}
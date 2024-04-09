package com.example.spring_boot_project_analytics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;
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
    private LocalDate month;
    private String productCategory;

    @ManyToOne
    @JoinColumn(name = "metadataId")
    private TableDetails metadata;
}
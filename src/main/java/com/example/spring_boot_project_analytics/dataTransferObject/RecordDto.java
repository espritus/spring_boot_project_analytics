package com.example.spring_boot_project_analytics.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {
    private Long id;
    private int product_id;
    private String YYYY_MM;
    private double balanceStart;
    private int min;
    private int max;
    private double transit;
    private double backorder;
    private int sales;
    private String productCategory;

}

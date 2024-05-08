package com.example.spring_boot_project_analytics.MainTable.entity;

import com.example.spring_boot_project_analytics.TableDetails.entity.TableDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "data_company_turnover")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "metadataId")
    private TableDetails metadata;

}

package com.example.spring_boot_project_analytics.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@lombok.Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class TableDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATETIME")
    private LocalDate uploadDate;
    private String tableName;
    private int rowCount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "metadataId")
    private List<Record> records = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "metadataId")
    private List<ForecastResult> forecastResults = new ArrayList<>();

}

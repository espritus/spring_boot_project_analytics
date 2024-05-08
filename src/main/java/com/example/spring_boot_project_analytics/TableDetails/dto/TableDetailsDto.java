package com.example.spring_boot_project_analytics.TableDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDetailsDto {
    private Long id;
    private LocalDate uploadDate;
    private String tableName;
    private int rowCount;
}

package com.example.spring_boot_project_analytics.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqDto {
    private long id;
    private String question;
    private String answer;
}

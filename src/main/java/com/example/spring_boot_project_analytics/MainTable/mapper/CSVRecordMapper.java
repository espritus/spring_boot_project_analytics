package com.example.spring_boot_project_analytics.MainTable.mapper;

import com.example.spring_boot_project_analytics.MainTable.dto.RecordDto;
import com.example.spring_boot_project_analytics.MainTable.entity.Record;

public class CSVRecordMapper {
    public static RecordDto mapToDataDto(Record data) {
        return new RecordDto(
                data.getId(),
                data.getProduct_id(),
                data.getYYYY_MM(),
                data.getBalanceStart(),
                data.getMin(),
                data.getMax(),
                data.getTransit(),
                data.getBackorder(),
                data.getSales(),
                data.getProductCategory()
        );

    }

    public static Record mapToData(RecordDto recordDto) {
        return new Record(
                null,
                recordDto.getProduct_id(),
                recordDto.getYYYY_MM(),
                recordDto.getBalanceStart(),
                recordDto.getMin(),
                recordDto.getMax(),
                recordDto.getTransit(),
                recordDto.getBackorder(),
                recordDto.getSales(),
                recordDto.getProductCategory(),
                null
        );
    }
}

package com.example.spring_boot_project_analytics.mapper;

import com.example.spring_boot_project_analytics.dataTransferObject.RecordDto;
import com.example.spring_boot_project_analytics.entity.Record;

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
                recordDto.getId(),
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

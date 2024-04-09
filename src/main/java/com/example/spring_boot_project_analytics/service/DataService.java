package com.example.spring_boot_project_analytics.service;


import com.example.spring_boot_project_analytics.dataTransferObject.RecordDto;
import com.example.spring_boot_project_analytics.dataTransferObject.TableDetailsDto;
import com.example.spring_boot_project_analytics.entity.ForecastResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DataService {
    TableDetailsDto updateTableMetaData(Long data_id);
    List<RecordDto> getAllTableStoredData(Long metaId);
    TableDetailsDto deleteTable(Long metaId);
    RecordDto deleteRow(Long data_id);
    CompletableFuture<List<ForecastResult>> sendFileToFlaskAndGetForecastAsync(MultipartFile file);
    RecordDto addRow(Long metaId, RecordDto RecordDto);
    void save(MultipartFile multipartFile);
    List<RecordDto> getAllStoredData();
    List<TableDetailsDto> getAllStoredMetaData();

}

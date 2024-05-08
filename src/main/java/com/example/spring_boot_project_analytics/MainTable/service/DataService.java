package com.example.spring_boot_project_analytics.MainTable.service;


import com.example.spring_boot_project_analytics.MainTable.dto.RecordDto;
import com.example.spring_boot_project_analytics.TableDetails.dto.TableDetailsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DataService {
    TableDetailsDto updateTableMetaData(Long data_id);
    List<RecordDto> getAllTableStoredData(Long metaId);
    TableDetailsDto deleteTable(Long metaId);
    RecordDto deleteRow(Long data_id);
    RecordDto addRow(Long metaId, RecordDto RecordDto);
    void save(MultipartFile multipartFile);
    List<RecordDto> getAllStoredData();
    List<TableDetailsDto> getAllStoredMetaData();

}

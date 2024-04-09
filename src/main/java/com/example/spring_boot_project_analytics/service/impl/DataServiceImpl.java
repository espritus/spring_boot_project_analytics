package com.example.spring_boot_project_analytics.service.impl;

import com.example.spring_boot_project_analytics.Flask.TaskStatus;
import com.example.spring_boot_project_analytics.Helper.MetaDataHelper;
import com.example.spring_boot_project_analytics.dataTransferObject.RecordDto;
import com.example.spring_boot_project_analytics.dataTransferObject.TableDetailsDto;
import com.example.spring_boot_project_analytics.entity.ForecastResult;
import com.example.spring_boot_project_analytics.entity.Record;
import com.example.spring_boot_project_analytics.Helper.CSVHelper;
import com.example.spring_boot_project_analytics.entity.TableDetails;
import com.example.spring_boot_project_analytics.exception.ResourceNotFoundException;
import com.example.spring_boot_project_analytics.mapper.CSVRecordMapper;
import com.example.spring_boot_project_analytics.mapper.TableDetailsMapper;
import com.example.spring_boot_project_analytics.repository.ForecastResultRepository;
import com.example.spring_boot_project_analytics.repository.RecordsRepository;
import com.example.spring_boot_project_analytics.repository.TableDetailsRepository;
import com.example.spring_boot_project_analytics.service.DataService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DataServiceImpl implements DataService {

    private static final long POLLING_INTERVAL = 60000 ;
    private static final int MAX_ATTEMPTS = 10;
    private RestTemplate restTemplate;
    private TransactionTemplate transactionTemplate;

    private RecordsRepository recordsRepository;
    private TableDetailsRepository metaDataRepository;
    private ForecastResultRepository forecastResultRepository;

    @Override
    public TableDetailsDto updateTableMetaData(Long metaId) {
        TableDetails table = metaDataRepository.findById(metaId).orElseThrow(
                () -> new ResourceNotFoundException("That file does not exist with the given id: " + metaId)
        );

        List<RecordDto> records = getAllTableStoredData(metaId);
        table.setRowCount(records.size());
        TableDetails updatedTable = metaDataRepository.save(table);

        return TableDetailsMapper.mapToTableMetaDataDto(updatedTable);
    }

    @Override
    public List<RecordDto> getAllTableStoredData(Long metaId) {
        TableDetails table = metaDataRepository.findById(metaId).orElseThrow(
                () -> new ResourceNotFoundException("That file does not exist with the given id: " + metaId)
        );
        List<Record> records = table.getRecords();
        return records.stream().map(CSVRecordMapper::mapToDataDto).
                collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TableDetailsDto deleteTable(Long metaId) {
        TableDetails table = metaDataRepository.findById(metaId).orElseThrow(
                () -> new ResourceNotFoundException("That file does not exist with the given id: " + metaId)
        );
        metaDataRepository.deleteById(metaId);
        return null;

    }


    @Override
    public RecordDto addRow(Long metaId,RecordDto recordDto) {
        TableDetails tableDetails = metaDataRepository.findById(metaId)
                .orElseThrow(() -> new ResourceNotFoundException("Meta table not found with id: " + metaId));

        Record data = CSVRecordMapper.mapToData(recordDto);
        data.setMetadata(tableDetails);

        System.out.println(data.getYYYY_MM() + " is the Date");
        System.out.println(data.getProductCategory() + " is the category");

        Record savedDATA = recordsRepository.save(data);
        return CSVRecordMapper.mapToDataDto(savedDATA);
    }
    @Override
    public void save(MultipartFile multipartFile) {
        transactionTemplate.execute(status -> {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                if (multipartFile.isEmpty()) {
                    throw new IllegalArgumentException("File is empty.");
                }

                List<Record> records = CSVHelper.csvToRecord(inputStream);
                if (records.isEmpty()) {
                    throw new IllegalArgumentException("CSV file does not contain any data.");
                }

                TableDetails metaData = MetaDataHelper.tableMetaData(records.size(), multipartFile.getOriginalFilename(), records, null);
                metaDataRepository.save(metaData);

                records.forEach(record -> record.setMetadata(metaData));
                recordsRepository.saveAll(records);

                 return null; // Required by the execute method
            } catch (Exception e) {
                throw new RuntimeException("Failed to process file: " + e.getMessage(), e);
            }
        });
    }


   
    @Override
    @Transactional
    public RecordDto deleteRow(Long data_id) {
        Record data = recordsRepository.findById(data_id).orElseThrow(
                () -> new ResourceNotFoundException("That row does not exist with the given id: " + data_id)
        );
        recordsRepository.deleteById(data_id);
        return null;
    }


    @Override
    public List<RecordDto> getAllStoredData() {
        List<Record> rows = recordsRepository.findAll();
        return rows.stream().map(CSVRecordMapper::mapToDataDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TableDetailsDto> getAllStoredMetaData() {
        List<TableDetails> tables = metaDataRepository.findAll();
        return tables.stream().map(TableDetailsMapper::mapToTableMetaDataDto)
                .collect(Collectors.toList());
    }
}

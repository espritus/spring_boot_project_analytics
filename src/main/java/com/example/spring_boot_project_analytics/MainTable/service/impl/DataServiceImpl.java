package com.example.spring_boot_project_analytics.MainTable.service.impl;

import com.example.spring_boot_project_analytics.MainTable.Helper.CSVHelper;
import com.example.spring_boot_project_analytics.MainTable.Helper.MetaDataHelper;
import com.example.spring_boot_project_analytics.MainTable.dto.RecordDto;
import com.example.spring_boot_project_analytics.TableDetails.dto.TableDetailsDto;
import com.example.spring_boot_project_analytics.MainTable.entity.Record;
import com.example.spring_boot_project_analytics.TableDetails.entity.TableDetails;
import com.example.spring_boot_project_analytics.exception.ResourceNotFoundException;
import com.example.spring_boot_project_analytics.MainTable.mapper.CSVRecordMapper;
import com.example.spring_boot_project_analytics.TableDetails.mapper.TableDetailsMapper;
import com.example.spring_boot_project_analytics.MainTable.repository.RecordsRepository;
import com.example.spring_boot_project_analytics.TableDetails.repository.TableDetailsRepository;
import com.example.spring_boot_project_analytics.MainTable.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DataServiceImpl implements DataService {

    private TransactionTemplate transactionTemplate;

    private RecordsRepository recordsRepository;
    private TableDetailsRepository metaDataRepository;


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

                TableDetails metaData = MetaDataHelper.getTableMetaData(records.size(), multipartFile.getOriginalFilename(), records, null);
                metaDataRepository.save(metaData);

                records.forEach(record -> record.setMetadata(metaData));
                recordsRepository.saveAll(records);

                 return null;
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

package com.example.spring_boot_project_analytics.controller;

import com.example.spring_boot_project_analytics.MainTable.dto.RecordDto;
import com.example.spring_boot_project_analytics.MainTable.service.DataService;
import com.example.spring_boot_project_analytics.Forecast.service.ForecastService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/datatables/{metaId}")
public class MainTableController {

    private DataService dataService;
    private ForecastService forecastService;

    //Build Get All BY ID (SPECIFIC TABLE) DATA REST API
    @GetMapping
    public ResponseEntity<List<RecordDto>> getAllTableStoredData(@PathVariable("metaId") Long metaId){
        List<RecordDto> records = dataService.getAllTableStoredData(metaId);
        return ResponseEntity.ok(records);
    }
    @PostMapping("/upload-process")
    public ResponseEntity<String> uploadToFlask(@PathVariable("metaId") Long metaId) {
        try {
            // Вызов метода сервиса для отправки файла и получения прогноза
            forecastService.sendFileToFlaskAndGetForecastAsync(metaId);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully wait for forecast");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }


    //Build POST ROW DATA REST API
    @PostMapping
    public ResponseEntity<RecordDto> addData(@PathVariable("metaId") Long metaId,
                                             @RequestBody RecordDto RecordDto){
        RecordDto saved_data = dataService.addRow(metaId,RecordDto);
        dataService.updateTableMetaData(metaId);
        return new ResponseEntity<>(saved_data, HttpStatus.CREATED);
    }

    // Build DELETE TABLE DATA REST API
    @DeleteMapping
    public ResponseEntity<?> deleteTable(@PathVariable("metaId") Long metaId) {
        dataService.deleteTable(metaId);
        return ResponseEntity.ok().build();
    }

    // Build DELETE ROW DATA REST API
    @DeleteMapping("{rowId}")
    public ResponseEntity<?> deleteRowById(@PathVariable("metaId") Long metaId,
                                           @PathVariable("rowId") Long rowId) {
        dataService.deleteRow(rowId);
        dataService.updateTableMetaData(metaId);
        return ResponseEntity.ok().build();
    }

}

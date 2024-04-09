package com.example.spring_boot_project_analytics.controller;

import com.example.spring_boot_project_analytics.dataTransferObject.RecordDto;
import com.example.spring_boot_project_analytics.service.DataService;
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

    //Build Get All BY ID (SPECIFIC TABLE) DATA REST API
    @GetMapping
    public ResponseEntity<List<RecordDto>> getAllTableStoredData(@PathVariable("metaId") Long metaId){
        List<RecordDto> records = dataService.getAllTableStoredData(metaId);
        return ResponseEntity.ok(records);
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

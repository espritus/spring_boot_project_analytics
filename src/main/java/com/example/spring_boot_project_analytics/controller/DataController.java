package com.example.spring_boot_project_analytics.controller;

import com.example.spring_boot_project_analytics.Helper.CSVHelper;
import com.example.spring_boot_project_analytics.dataTransferObject.RecordDto;
import com.example.spring_boot_project_analytics.dataTransferObject.TableDetailsDto;
import com.example.spring_boot_project_analytics.message.ResponseMessage;
import com.example.spring_boot_project_analytics.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/datatables")
public class DataController {

    private DataService dataService;

    //Build Post DATA_Table REST API
    @Transactional
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                dataService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: "+ e +" - " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    //Build Get All DATA REST API
    @GetMapping("/records")
    public ResponseEntity<List<RecordDto>> getAllRecords() {
        List<RecordDto> rows = dataService.getAllStoredData();
        return ResponseEntity.ok(rows);
    }

    //Build Get All META DATA REST API
    @GetMapping
    public ResponseEntity<List<TableDetailsDto>> getAllStoredMetaData(){
        List<TableDetailsDto> tables = dataService.getAllStoredMetaData();
        return ResponseEntity.ok(tables);
    }

}

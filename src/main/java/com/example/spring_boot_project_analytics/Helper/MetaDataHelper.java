package com.example.spring_boot_project_analytics.Helper;

import com.example.spring_boot_project_analytics.entity.ForecastResult;
import com.example.spring_boot_project_analytics.entity.TableDetails;
import com.example.spring_boot_project_analytics.entity.Record;

import java.time.LocalDate;
import java.util.List;

public class MetaDataHelper {

    public static TableDetails tableMetaData(int size, String tableName,
                                             List<Record> records, List<ForecastResult> forecastResults){
        LocalDate localDate = LocalDate.now();
        return new TableDetails(
                null,
                localDate,
                tableName,
                size,
                records,
                forecastResults

        );

    }
}

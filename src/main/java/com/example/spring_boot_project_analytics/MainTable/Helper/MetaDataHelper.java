package com.example.spring_boot_project_analytics.MainTable.Helper;

import com.example.spring_boot_project_analytics.Forecast.entity.ForecastResult;
import com.example.spring_boot_project_analytics.TableDetails.entity.TableDetails;
import com.example.spring_boot_project_analytics.MainTable.entity.Record;

import java.time.LocalDate;
import java.util.List;

public class MetaDataHelper {

    public static TableDetails getTableMetaData(int size, String tableName,
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

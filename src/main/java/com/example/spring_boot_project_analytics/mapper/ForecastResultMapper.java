package com.example.spring_boot_project_analytics.mapper;

import com.example.spring_boot_project_analytics.dataTransferObject.ForecastResultDto;
import com.example.spring_boot_project_analytics.entity.ForecastResult;

public class ForecastResultMapper {

    public static ForecastResultDto mapToForecastResultDto(ForecastResult forecastResult){
        return new ForecastResultDto(
                forecastResult.getProductId(),
                forecastResult.getPredictedSales(),
                forecastResult.getExpenditure(),
                forecastResult.getBalanceEnd(),
                forecastResult.getBalanceStart(),
                forecastResult.getTurnover(),
                forecastResult.getTurnoverDiff(),
                forecastResult.getOrders(),
                forecastResult.getArrival(),
                forecastResult.getBackorder(),
                forecastResult.getTransit(),
                forecastResult.getMonth(),
                forecastResult.getProductCategory()
        );
    }
//    public static ForecastResult mapToForecastResult(ForecastResultDto forecastResult){
//        return new ForecastResult(
//                forecastResult.getProductId(),
//                forecastResult.getPredictedSales(),
//                forecastResult.getExpenditure(),
//                forecastResult.getBalanceEnd(),
//                forecastResult.getBalanceStart(),
//                forecastResult.getTurnover(),
//                forecastResult.getTurnoverDiff(),
//                forecastResult.getOrders(),
//                forecastResult.getArrival(),
//                forecastResult.getBackorder(),
//                forecastResult.getTransit(),
//                forecastResult.getMonth(),
//                forecastResult.getProductCategory()
//        );
//    }
}

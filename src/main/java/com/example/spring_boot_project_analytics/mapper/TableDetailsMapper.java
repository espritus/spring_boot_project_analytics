package com.example.spring_boot_project_analytics.mapper;

import com.example.spring_boot_project_analytics.dataTransferObject.TableDetailsDto;
import com.example.spring_boot_project_analytics.entity.TableDetails;

public class TableDetailsMapper {

    public static TableDetailsDto mapToTableMetaDataDto(TableDetails metaData){
        return new TableDetailsDto(
                metaData.getId(),
                metaData.getUploadDate(),
                metaData.getTableName(),
                metaData.getRowCount()
        );
    }

//    public static TableMetaData mapToTableMetaData(TableMetaDataDto metaDataDto){
//        return new TableMetaData(
//                metaDataDto.getId(),
//                metaDataDto.getUploadDate(),
//                metaDataDto.getTableName(),
//                metaDataDto.getRowCount()
//        );
//    }
}

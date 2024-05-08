package com.example.spring_boot_project_analytics.TableDetails.mapper;

import com.example.spring_boot_project_analytics.TableDetails.dto.TableDetailsDto;
import com.example.spring_boot_project_analytics.TableDetails.entity.TableDetails;

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

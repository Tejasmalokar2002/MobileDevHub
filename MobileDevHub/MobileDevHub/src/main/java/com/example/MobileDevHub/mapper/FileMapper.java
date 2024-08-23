package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.FileDTO;
import com.example.MobileDevHub.entity.FileUpload;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileDTO toDTO(FileUpload fileUpload);
    FileUpload toEntity(FileDTO fileDTO);
}

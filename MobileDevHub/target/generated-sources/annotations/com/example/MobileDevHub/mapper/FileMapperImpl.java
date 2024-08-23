package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.FileDTO;
import com.example.MobileDevHub.entity.FileUpload;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-23T05:57:24-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class FileMapperImpl implements FileMapper {

    @Override
    public FileDTO toDTO(FileUpload fileUpload) {
        if ( fileUpload == null ) {
            return null;
        }

        FileDTO fileDTO = new FileDTO();

        fileDTO.setId( fileUpload.getId() );
        fileDTO.setFileName( fileUpload.getFileName() );

        return fileDTO;
    }

    @Override
    public FileUpload toEntity(FileDTO fileDTO) {
        if ( fileDTO == null ) {
            return null;
        }

        FileUpload fileUpload = new FileUpload();

        fileUpload.setId( fileDTO.getId() );
        fileUpload.setFileName( fileDTO.getFileName() );

        return fileUpload;
    }
}

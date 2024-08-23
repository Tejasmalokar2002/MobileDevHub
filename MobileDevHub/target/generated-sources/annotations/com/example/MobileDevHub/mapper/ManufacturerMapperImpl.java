package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.ManufacturerDTO;
import com.example.MobileDevHub.entity.Manufacturer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-23T05:57:24-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class ManufacturerMapperImpl implements ManufacturerMapper {

    @Override
    public ManufacturerDTO toDTO(Manufacturer manufacturer) {
        if ( manufacturer == null ) {
            return null;
        }

        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();

        manufacturerDTO.setId( manufacturer.getId() );
        manufacturerDTO.setName( manufacturer.getName() );
        manufacturerDTO.setAddress( manufacturer.getAddress() );
        manufacturerDTO.setContactNumber( manufacturer.getContactNumber() );
        manufacturerDTO.setEmail( manufacturer.getEmail() );
        manufacturerDTO.setEstablishedDate( manufacturer.getEstablishedDate() );

        return manufacturerDTO;
    }

    @Override
    public Manufacturer toEntity(ManufacturerDTO manufacturerDTO) {
        if ( manufacturerDTO == null ) {
            return null;
        }

        Manufacturer manufacturer = new Manufacturer();

        manufacturer.setId( manufacturerDTO.getId() );
        manufacturer.setName( manufacturerDTO.getName() );
        manufacturer.setAddress( manufacturerDTO.getAddress() );
        manufacturer.setContactNumber( manufacturerDTO.getContactNumber() );
        manufacturer.setEmail( manufacturerDTO.getEmail() );
        manufacturer.setEstablishedDate( manufacturerDTO.getEstablishedDate() );

        return manufacturer;
    }

    @Override
    public Manufacturer updateEntityFromDTO(ManufacturerDTO manufacturerDTO, Manufacturer manufacturer) {
        if ( manufacturerDTO == null ) {
            return manufacturer;
        }

        manufacturer.setName( manufacturerDTO.getName() );
        manufacturer.setAddress( manufacturerDTO.getAddress() );
        manufacturer.setContactNumber( manufacturerDTO.getContactNumber() );
        manufacturer.setEmail( manufacturerDTO.getEmail() );
        manufacturer.setEstablishedDate( manufacturerDTO.getEstablishedDate() );

        return manufacturer;
    }
}

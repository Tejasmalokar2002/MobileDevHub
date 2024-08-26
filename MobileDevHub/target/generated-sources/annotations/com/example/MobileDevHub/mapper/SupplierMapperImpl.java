package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.SupplierDTO;
import com.example.MobileDevHub.entity.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T02:15:47-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SupplierDTO toDTO(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDTO supplierDTO = new SupplierDTO();

        supplierDTO.setSupplier_id( supplier.getSupplier_id() );
        supplierDTO.setName( supplier.getName() );
        supplierDTO.setContactNumber( supplier.getContactNumber() );
        supplierDTO.setEmail( supplier.getEmail() );
        supplierDTO.setAddress( supplier.getAddress() );
        supplierDTO.setSupplyDate( supplier.getSupplyDate() );

        return supplierDTO;
    }

    @Override
    public Supplier toEntity(SupplierDTO supplierDTO) {
        if ( supplierDTO == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setSupplier_id( supplierDTO.getSupplier_id() );
        supplier.setName( supplierDTO.getName() );
        supplier.setContactNumber( supplierDTO.getContactNumber() );
        supplier.setEmail( supplierDTO.getEmail() );
        supplier.setAddress( supplierDTO.getAddress() );
        supplier.setSupplyDate( supplierDTO.getSupplyDate() );

        return supplier;
    }

    @Override
    public Supplier updateEntityFromDTO(SupplierDTO supplierDTO, Supplier supplier) {
        if ( supplierDTO == null ) {
            return supplier;
        }

        supplier.setName( supplierDTO.getName() );
        supplier.setContactNumber( supplierDTO.getContactNumber() );
        supplier.setEmail( supplierDTO.getEmail() );
        supplier.setAddress( supplierDTO.getAddress() );
        supplier.setSupplyDate( supplierDTO.getSupplyDate() );

        return supplier;
    }
}

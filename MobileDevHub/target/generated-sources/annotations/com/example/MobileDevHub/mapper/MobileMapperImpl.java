package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.MobileDTO;
import com.example.MobileDevHub.entity.Manufacturer;
import com.example.MobileDevHub.entity.Mobile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T02:15:47-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class MobileMapperImpl implements MobileMapper {

    @Override
    public MobileDTO toDTO(Mobile mobile) {
        if ( mobile == null ) {
            return null;
        }

        MobileDTO mobileDTO = new MobileDTO();

        mobileDTO.setManufacturerId( mobileManufacturerId( mobile ) );
        mobileDTO.setMobile_id( mobile.getMobile_id() );
        mobileDTO.setMobileName( mobile.getMobileName() );
        mobileDTO.setBrand( mobile.getBrand() );
        mobileDTO.setPrice( mobile.getPrice() );
        mobileDTO.setReleaseDate( mobile.getReleaseDate() );
        mobileDTO.setSpecification( mobile.getSpecification() );
        mobileDTO.setQuantityInStock( mobile.getQuantityInStock() );
        mobileDTO.setManufacturingDate( mobile.getManufacturingDate() );
        mobileDTO.setWarrantyPeriod( mobile.getWarrantyPeriod() );

        return mobileDTO;
    }

    @Override
    public Mobile toEntity(MobileDTO mobileDTO) {
        if ( mobileDTO == null ) {
            return null;
        }

        Mobile mobile = new Mobile();

        mobile.setMobile_id( mobileDTO.getMobile_id() );
        mobile.setMobileName( mobileDTO.getMobileName() );
        mobile.setBrand( mobileDTO.getBrand() );
        mobile.setPrice( mobileDTO.getPrice() );
        mobile.setReleaseDate( mobileDTO.getReleaseDate() );
        mobile.setSpecification( mobileDTO.getSpecification() );
        mobile.setQuantityInStock( mobileDTO.getQuantityInStock() );
        mobile.setManufacturingDate( mobileDTO.getManufacturingDate() );
        mobile.setWarrantyPeriod( mobileDTO.getWarrantyPeriod() );

        return mobile;
    }

    @Override
    public Mobile updateEntityFromDTO(MobileDTO mobileDTO, Mobile mobile) {
        if ( mobileDTO == null ) {
            return mobile;
        }

        mobile.setMobileName( mobileDTO.getMobileName() );
        mobile.setBrand( mobileDTO.getBrand() );
        mobile.setPrice( mobileDTO.getPrice() );
        mobile.setReleaseDate( mobileDTO.getReleaseDate() );
        mobile.setSpecification( mobileDTO.getSpecification() );
        mobile.setQuantityInStock( mobileDTO.getQuantityInStock() );
        mobile.setManufacturingDate( mobileDTO.getManufacturingDate() );
        mobile.setWarrantyPeriod( mobileDTO.getWarrantyPeriod() );

        return mobile;
    }

    private Long mobileManufacturerId(Mobile mobile) {
        Manufacturer manufacturer = mobile.getManufacturer();
        if ( manufacturer == null ) {
            return null;
        }
        return manufacturer.getId();
    }
}

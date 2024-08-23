package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.MobileDTO;
import com.example.MobileDevHub.entity.Mobile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface MobileMapper {

    @Mapping(source = "manufacturer.id", target = "manufacturerId")
    MobileDTO toDTO(Mobile mobile);

    @Mapping(target = "manufacturer", ignore = true) // To be handled in the service layer
    Mobile toEntity(MobileDTO mobileDTO);

    @Mapping(target = "mobile_id", ignore = true)
    @Mapping(target = "manufacturer", ignore = true) // To be handled in the service layer
    Mobile updateEntityFromDTO(MobileDTO mobileDTO, @MappingTarget Mobile mobile);
}


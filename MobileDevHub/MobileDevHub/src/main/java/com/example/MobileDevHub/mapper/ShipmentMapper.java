package com.example.MobileDevHub.mapper;


import com.example.MobileDevHub.dto.ShipmentDTO;
import com.example.MobileDevHub.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {
    @Mapping(source = "orderId", target = "order.id")
    Shipment toEntity(ShipmentDTO shipmentDTO);

    @Mapping(source = "order.id", target = "orderId")
    ShipmentDTO toDTO(Shipment shipment);

    @Mapping(target = "id", ignore = true) // Ignore ID during updates if not required
    Shipment updateEntityFromDTO(ShipmentDTO shipmentDTO, @MappingTarget Shipment shipment);
}

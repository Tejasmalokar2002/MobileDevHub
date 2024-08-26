package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.ShipmentDTO;
import com.example.MobileDevHub.entity.Order;
import com.example.MobileDevHub.entity.Shipment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T02:15:46-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class ShipmentMapperImpl implements ShipmentMapper {

    @Override
    public Shipment toEntity(ShipmentDTO shipmentDTO) {
        if ( shipmentDTO == null ) {
            return null;
        }

        Shipment shipment = new Shipment();

        shipment.setOrder( shipmentDTOToOrder( shipmentDTO ) );
        shipment.setId( shipmentDTO.getId() );
        shipment.setShipmentNumber( shipmentDTO.getShipmentNumber() );
        shipment.setShipmentDate( shipmentDTO.getShipmentDate() );
        shipment.setDeliveryDate( shipmentDTO.getDeliveryDate() );
        shipment.setStatus( shipmentDTO.getStatus() );

        return shipment;
    }

    @Override
    public ShipmentDTO toDTO(Shipment shipment) {
        if ( shipment == null ) {
            return null;
        }

        ShipmentDTO shipmentDTO = new ShipmentDTO();

        shipmentDTO.setOrderId( shipmentOrderId( shipment ) );
        shipmentDTO.setId( shipment.getId() );
        shipmentDTO.setShipmentNumber( shipment.getShipmentNumber() );
        shipmentDTO.setShipmentDate( shipment.getShipmentDate() );
        shipmentDTO.setDeliveryDate( shipment.getDeliveryDate() );
        shipmentDTO.setStatus( shipment.getStatus() );

        return shipmentDTO;
    }

    @Override
    public Shipment updateEntityFromDTO(ShipmentDTO shipmentDTO, Shipment shipment) {
        if ( shipmentDTO == null ) {
            return shipment;
        }

        shipment.setShipmentNumber( shipmentDTO.getShipmentNumber() );
        shipment.setShipmentDate( shipmentDTO.getShipmentDate() );
        shipment.setDeliveryDate( shipmentDTO.getDeliveryDate() );
        shipment.setStatus( shipmentDTO.getStatus() );

        return shipment;
    }

    protected Order shipmentDTOToOrder(ShipmentDTO shipmentDTO) {
        if ( shipmentDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( shipmentDTO.getOrderId() );

        return order;
    }

    private Long shipmentOrderId(Shipment shipment) {
        Order order = shipment.getOrder();
        if ( order == null ) {
            return null;
        }
        return order.getId();
    }
}

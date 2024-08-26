package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.OrderDTO;
import com.example.MobileDevHub.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T02:15:47-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDTO.getId() );
        order.setOrderDate( orderDTO.getOrderDate() );
        order.setQuantity( orderDTO.getQuantity() );
        order.setTotalPrice( orderDTO.getTotalPrice() );
        order.setStatus( orderDTO.getStatus() );

        return order;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setMobileId( mobileToMobileId( order.getMobile() ) );
        orderDTO.setSupplierId( supplierToSupplierId( order.getSupplier() ) );
        orderDTO.setId( order.getId() );
        orderDTO.setOrderDate( order.getOrderDate() );
        orderDTO.setQuantity( order.getQuantity() );
        orderDTO.setTotalPrice( order.getTotalPrice() );
        orderDTO.setStatus( order.getStatus() );

        return orderDTO;
    }

    @Override
    public Order updateEntityFromDTO(OrderDTO orderDTO, Order order) {
        if ( orderDTO == null ) {
            return order;
        }

        order.setOrderDate( orderDTO.getOrderDate() );
        order.setQuantity( orderDTO.getQuantity() );
        order.setTotalPrice( orderDTO.getTotalPrice() );
        order.setStatus( orderDTO.getStatus() );

        return order;
    }
}

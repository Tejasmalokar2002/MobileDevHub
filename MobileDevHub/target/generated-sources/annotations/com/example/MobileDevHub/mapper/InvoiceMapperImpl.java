package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.InvoiceDTO;
import com.example.MobileDevHub.entity.Invoice;
import com.example.MobileDevHub.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T02:15:47-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceDTO toDTO(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setOrderId( invoiceOrderId( invoice ) );
        invoiceDTO.setId( invoice.getId() );
        invoiceDTO.setInvoiceNumber( invoice.getInvoiceNumber() );
        invoiceDTO.setInvoiceDate( invoice.getInvoiceDate() );
        invoiceDTO.setTotalAmount( invoice.getTotalAmount() );
        invoiceDTO.setPaymentStatus( invoice.getPaymentStatus() );

        return invoiceDTO;
    }

    @Override
    public Invoice toEntity(InvoiceDTO invoiceDTO) {
        if ( invoiceDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setId( invoiceDTO.getId() );
        invoice.setInvoiceNumber( invoiceDTO.getInvoiceNumber() );
        invoice.setInvoiceDate( invoiceDTO.getInvoiceDate() );
        invoice.setTotalAmount( invoiceDTO.getTotalAmount() );
        invoice.setPaymentStatus( invoiceDTO.getPaymentStatus() );

        return invoice;
    }

    @Override
    public Invoice updateEntityFromDTO(InvoiceDTO invoiceDTO, Invoice invoice) {
        if ( invoiceDTO == null ) {
            return invoice;
        }

        invoice.setInvoiceNumber( invoiceDTO.getInvoiceNumber() );
        invoice.setInvoiceDate( invoiceDTO.getInvoiceDate() );
        invoice.setTotalAmount( invoiceDTO.getTotalAmount() );
        invoice.setPaymentStatus( invoiceDTO.getPaymentStatus() );

        return invoice;
    }

    private Long invoiceOrderId(Invoice invoice) {
        Order order = invoice.getOrder();
        if ( order == null ) {
            return null;
        }
        return order.getId();
    }
}

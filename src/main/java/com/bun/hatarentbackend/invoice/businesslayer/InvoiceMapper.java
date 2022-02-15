package com.bun.hatarentbackend.invoice.businesslayer;

import com.bun.hatarentbackend.invoice.datalayer.Invoice;
import com.bun.hatarentbackend.invoice.datalayer.InvoiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(target = "invoiceId", ignore = true)
        Invoice invoiceDTOToInvoiceEntity(InvoiceDTO invoiceDTO);
        InvoiceDTO invoiceEntityToInvoiceDTO(Invoice invoice);
}

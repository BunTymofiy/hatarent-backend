package com.bun.hatarentbackend.invoice.businesslayer;

import com.bun.hatarentbackend.invoice.datalayer.Invoice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceService {
    List<Invoice> findAll();
    Optional<Invoice> findByUuid(UUID invoiceId);
    Invoice update(Invoice invoice);
    Invoice create(Invoice invoice);
    void delete(UUID invoiceId);
}

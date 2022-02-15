package com.bun.hatarentbackend.invoice.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    Integer deleteByInvoiceId(UUID invoiceId);
}

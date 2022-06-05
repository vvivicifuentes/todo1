package com.hulkstore.todo1.app.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hulkstore.todo1.app.invoice.entity.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public Invoice findByNumberInvoice(String numberInvoice);
}
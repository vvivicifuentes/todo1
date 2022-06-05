package com.hulkstore.todo1.app.invoice.service;

import java.util.List;

import com.hulkstore.todo1.app.invoice.entity.Invoice;


public interface InvoiceService {
    public List<Invoice> findInvoiceAll();

    public Invoice createInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);
    public Invoice getInvoice(Long id);
}
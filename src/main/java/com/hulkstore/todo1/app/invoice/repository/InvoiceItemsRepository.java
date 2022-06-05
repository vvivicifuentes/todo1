package com.hulkstore.todo1.app.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hulkstore.todo1.app.invoice.entity.InvoiceItem;


public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
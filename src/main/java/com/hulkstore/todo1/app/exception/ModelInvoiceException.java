package com.hulkstore.todo1.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ModelInvoiceException extends RuntimeException{
    public ModelInvoiceException(String message){
        super(message);
    }
}

package com.tes.Penjualan.exceptionHandler;

public class UniqueFieldException extends RuntimeException {
    public UniqueFieldException(String message){
        super(message);
    }
}

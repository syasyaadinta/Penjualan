package com.tes.Penjualan.exceptionHandler;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String message){
        super(message);
    }

}

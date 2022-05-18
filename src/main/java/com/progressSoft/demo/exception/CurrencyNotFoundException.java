package com.progressSoft.demo.exception;

public class CurrencyNotFoundException extends Exception{

    public CurrencyNotFoundException(String currency){
        super(currency + "not found in currencies list");

    }
}

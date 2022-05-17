package com.progressSoft.demo.service.exceptions;

public class CurrencyNotFoundException extends Exception{

    public CurrencyNotFoundException(String currency){
        super(currency + "not found in currencies list");

    }
}

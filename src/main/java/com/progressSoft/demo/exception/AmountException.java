package com.progressSoft.demo.exception;
/**
 * this exception catch any case when the amount is invalid
 * */
public class AmountException extends Exception{
    public AmountException(String exception){
        super(exception);
    }
}

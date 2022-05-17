/**
 * this exception catch if currency text includes not english alphabetical chars
 * */
package com.progressSoft.demo.service.exceptions;

public class InvalidCurrencyTextException extends Exception {

    public InvalidCurrencyTextException(String currency) {
        super(currency + "should be in english");
    }
}

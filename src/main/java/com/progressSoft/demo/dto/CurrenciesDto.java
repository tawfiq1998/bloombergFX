/**
 * this DTO to accept the response from the request to bring currencies
 * */
package com.progressSoft.demo.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CurrenciesDto {
    String success;
    Map<String,String> symbols;
}

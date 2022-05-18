package com.progressSoft.demo;


import com.progressSoft.demo.dto.CurrenciesDto;
import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.service.impl.ValidationServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class checkCurrencyTypeTest {
    @InjectMocks
    private ValidationServiceImpl validationService;

    @Mock
    RestTemplate restTemplate;
    @Value("${api.currencies.url}")
    private String currenciesApiURL;

    @Value("${api.currencies.key}")
    private String currenciesApiKey;

    @Test
    public void checkCurrencyTypeTestValid() {
        CurrenciesDto currenciesDto = new CurrenciesDto();
        Map<String, String> map = new HashMap<>();
        map.put("JOD", "Jordanian Dinar");
        map.put("USD", "United States Dollar");
        currenciesDto.setSymbols(map);
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", currenciesApiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Mockito
                .when(restTemplate.exchange(
                        currenciesApiURL, HttpMethod.GET, requestEntity, CurrenciesDto.class))
                .thenReturn(new ResponseEntity(currenciesDto, HttpStatus.OK));



        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();
        foreignExchangeTransactionDto.setFromCurrencyCode("JOD");
        foreignExchangeTransactionDto.setToCurrencyCode("USD");

        Assert.assertEquals("",validationService.checkCurrencyType(foreignExchangeTransactionDto));


        foreignExchangeTransactionDto.setFromCurrencyCode("jod");
        foreignExchangeTransactionDto.setToCurrencyCode("USD");

        Assert.assertEquals("",validationService.checkCurrencyType(foreignExchangeTransactionDto));

    }

    @Test
    public void checkCurrencyTypeTestBothNotValid() {
        CurrenciesDto currenciesDto = new CurrenciesDto();
        Map<String, String> map = new HashMap<>();
        map.put("JOD", "Jordanian Dinar");
        map.put("USD", "United States Dollar");
        currenciesDto.setSymbols(map);
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", currenciesApiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Mockito
                .when(restTemplate.exchange(
                        currenciesApiURL, HttpMethod.GET, requestEntity, CurrenciesDto.class))
                .thenReturn(new ResponseEntity(currenciesDto, HttpStatus.OK));



        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();
        foreignExchangeTransactionDto.setFromCurrencyCode("ABC");
        foreignExchangeTransactionDto.setToCurrencyCode("XYZ");

        Assert.assertEquals("FromCurrency, ToCurrency, ",validationService.checkCurrencyType(foreignExchangeTransactionDto));

    }

    @Test
    public void checkCurrencyTypeTestToCurrencyNotValid() {
        CurrenciesDto currenciesDto = new CurrenciesDto();
        Map<String, String> map = new HashMap<>();
        map.put("JOD", "Jordanian Dinar");
        map.put("USD", "United States Dollar");
        currenciesDto.setSymbols(map);
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", currenciesApiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Mockito
                .when(restTemplate.exchange(
                        currenciesApiURL, HttpMethod.GET, requestEntity, CurrenciesDto.class))
                .thenReturn(new ResponseEntity(currenciesDto, HttpStatus.OK));



        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();
        foreignExchangeTransactionDto.setFromCurrencyCode("USD");
        foreignExchangeTransactionDto.setToCurrencyCode("XYZ");

        Assert.assertEquals("ToCurrency, ",validationService.checkCurrencyType(foreignExchangeTransactionDto));

    }
    @Test
    public void checkCurrencyTypeTestFromCurrencyNotValid() {
        CurrenciesDto currenciesDto = new CurrenciesDto();
        Map<String, String> map = new HashMap<>();
        map.put("JOD", "Jordanian Dinar");
        map.put("USD", "United States Dollar");
        currenciesDto.setSymbols(map);
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", currenciesApiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Mockito
                .when(restTemplate.exchange(
                        currenciesApiURL, HttpMethod.GET, requestEntity, CurrenciesDto.class))
                .thenReturn(new ResponseEntity(currenciesDto, HttpStatus.OK));



        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();
        foreignExchangeTransactionDto.setFromCurrencyCode("ABC");
        foreignExchangeTransactionDto.setToCurrencyCode("USD");

        Assert.assertEquals("FromCurrency, ",validationService.checkCurrencyType(foreignExchangeTransactionDto));

    }



}

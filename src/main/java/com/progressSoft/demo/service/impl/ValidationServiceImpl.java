package com.progressSoft.demo.service.impl;

import com.progressSoft.demo.dto.CurrenciesDto;
import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.repository.ForeignExchangeTransactionRepository;
import com.progressSoft.demo.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidationServiceImpl implements ValidationService {


    @Autowired
    private ForeignExchangeTransactionRepository foreignExchangeTransactionRepository;


    @Value("${api.currencies.url}")
    private String currenciesApiURL;

    @Value("${api.currencies.key}")
    private String currenciesApiKey;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * this method validate currency text language (should be in english)
     */
    @Override
    public String validateCurrenciesText(ForeignExchangeTransactionDto foreignExchangeTransactionDto) {
        String notMatchesCurrency = "";
        if (!foreignExchangeTransactionDto.getFromCurrencyCode().matches("^[a-zA-Z]*$"))
            notMatchesCurrency = notMatchesCurrency.concat("FromCurrency, ");

        if (!foreignExchangeTransactionDto.getToCurrencyCode().matches("^[a-zA-Z]*$")) {
            notMatchesCurrency = notMatchesCurrency.concat("ToCurrency, ");
        }
        return notMatchesCurrency;
    }

    /**
     * this method validate currency if it's from currencies list
     */
    @Override
    public String checkCurrencyType(ForeignExchangeTransactionDto foreignExchangeTransactionDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", currenciesApiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        CurrenciesDto currenciesDto = restTemplate.exchange(
                currenciesApiURL, HttpMethod.GET, requestEntity, CurrenciesDto.class).getBody();

        String notMatchesCurrency = "";
        if (!currenciesDto.getSymbols().containsKey(foreignExchangeTransactionDto.getFromCurrencyCode().toUpperCase()))
            notMatchesCurrency = notMatchesCurrency.concat("FromCurrency, ");

        if (!currenciesDto.getSymbols().containsKey(foreignExchangeTransactionDto.getToCurrencyCode().toUpperCase()))
            notMatchesCurrency = notMatchesCurrency.concat("ToCurrency, ");

        return notMatchesCurrency;
    }

    /**
     * this method validate amount
     * */
    @Override
    public boolean validateAmount(double dealAmount) {
        return dealAmount >= 0;
    }

    /**
     * check if transaction being saved previously
     * */
    @Override
    public boolean checkIfExist(ForeignExchangeTransactionDto foreignExchangeTransactionDto) {
        Long numberOfForeignExchangeTransaction = foreignExchangeTransactionRepository.foreign_exchange_transaction(
                foreignExchangeTransactionDto.getFromCurrencyCode()
                , foreignExchangeTransactionDto.getToCurrencyCode()
                , foreignExchangeTransactionDto.getDealAmount()
                , foreignExchangeTransactionDto.getTransactionDate());

        return numberOfForeignExchangeTransaction > 0;
    }
}

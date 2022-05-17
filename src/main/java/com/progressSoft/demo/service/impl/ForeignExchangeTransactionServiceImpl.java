package com.progressSoft.demo.service.impl;

import com.progressSoft.demo.dto.CurrenciesDto;
import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.entity.ForeignExchangeTransaction;
import com.progressSoft.demo.repository.ForeignExchangeTransactionRepository;
import com.progressSoft.demo.service.ForeignExchangeTransactionService;
import com.progressSoft.demo.service.exceptions.AmountException;
import com.progressSoft.demo.service.exceptions.CurrencyNotFoundException;
import com.progressSoft.demo.service.exceptions.InvalidCurrencyTextException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForeignExchangeTransactionServiceImpl implements ForeignExchangeTransactionService {

    @Autowired
    private ForeignExchangeTransactionRepository foreignExchangeTransactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.currencies.url}")
    private String currenciesApiURL;

    @Value("${api.currencies.key}")
    private String currenciesApiKey;

    @Override
    public ResponseEntity<String> insertTransaction(ForeignExchangeTransactionDto foreignExchangeTransactionDto) throws Exception {
        String currencyMismatchLang = validateCurrenciesText(foreignExchangeTransactionDto);
        if (!currencyMismatchLang.isEmpty()) {
            throw new InvalidCurrencyTextException(currencyMismatchLang);
        }

        if (!validateAmount(foreignExchangeTransactionDto.getDealAmount())) {
            throw new AmountException("Amount should be positive");
        }

        String currencyNotInTheList=checkCurrencyType(foreignExchangeTransactionDto);
        if (!currencyNotInTheList.isEmpty()) {
            throw new CurrencyNotFoundException(currencyNotInTheList);
        }

        foreignExchangeTransactionRepository.save(mapForeignExchangeTransactionDtoToEntity(foreignExchangeTransactionDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("transaction inserted successfully ");

    }

    private ForeignExchangeTransaction mapForeignExchangeTransactionDtoToEntity(ForeignExchangeTransactionDto dto) {
        ForeignExchangeTransaction foreignExchangeTransaction = new ForeignExchangeTransaction();

        foreignExchangeTransaction.setTransactionDate(dto.getTransactionDate());
        foreignExchangeTransaction.setDealAmount(dto.getDealAmount());
        foreignExchangeTransaction.setFromCurrencyCode(dto.getFromCurrencyCode());
        foreignExchangeTransaction.setToCurrencyCode(dto.getToCurrencyCode());

        return foreignExchangeTransaction;
    }

    private String validateCurrenciesText(ForeignExchangeTransactionDto foreignExchangeTransactionDto) {
        String notMatchesCurrency = "";
        if (!foreignExchangeTransactionDto.getFromCurrencyCode().matches("^[a-zA-Z]*$"))
            notMatchesCurrency = notMatchesCurrency.concat("FromCurrency, ");

        if (!foreignExchangeTransactionDto.getToCurrencyCode().matches("^[a-zA-Z]*$")) {
            notMatchesCurrency = notMatchesCurrency.concat("ToCurrency, ");
        }
        return notMatchesCurrency;
    }

    private String checkCurrencyType(ForeignExchangeTransactionDto foreignExchangeTransactionDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", currenciesApiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        CurrenciesDto currenciesDto = restTemplate.exchange(
                currenciesApiURL, HttpMethod.GET, requestEntity, CurrenciesDto.class).getBody();
        String notMatchesCurrency = "";
        if (currenciesDto.getSymbols().containsKey(foreignExchangeTransactionDto.getFromCurrencyCode()))
            notMatchesCurrency = notMatchesCurrency.concat("FromCurrency, ");

        if (currenciesDto.getSymbols().containsKey(foreignExchangeTransactionDto.getToCurrencyCode()))
            notMatchesCurrency = notMatchesCurrency.concat("ToCurrency, ");

        return notMatchesCurrency;
    }

    private boolean validateAmount(double dealAmount) {
        return dealAmount >= 0;
    }
}

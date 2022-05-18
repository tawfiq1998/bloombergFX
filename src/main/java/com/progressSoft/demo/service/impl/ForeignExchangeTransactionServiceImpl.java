package com.progressSoft.demo.service.impl;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.entity.ForeignExchangeTransaction;
import com.progressSoft.demo.exception.AmountException;
import com.progressSoft.demo.exception.CurrencyNotFoundException;
import com.progressSoft.demo.exception.InvalidCurrencyTextException;
import com.progressSoft.demo.exception.TransactionExistException;
import com.progressSoft.demo.repository.ForeignExchangeTransactionRepository;
import com.progressSoft.demo.service.ForeignExchangeTransactionService;
import com.progressSoft.demo.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForeignExchangeTransactionServiceImpl implements ForeignExchangeTransactionService {

    @Autowired
    private ForeignExchangeTransactionRepository foreignExchangeTransactionRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ResponseEntity<String> insertTransaction(ForeignExchangeTransactionDto foreignExchangeTransactionDto) throws Exception {
        String currencyMismatchLang = validationService.validateCurrenciesText(foreignExchangeTransactionDto);
        /**
         *  check if currency text invalid throw exception
         * */
        if (!currencyMismatchLang.isEmpty()) {
            throw new InvalidCurrencyTextException(currencyMismatchLang);
        }

        /**
         * if amount value is not valid throw exception
         * */
        if (!validationService.validateAmount(foreignExchangeTransactionDto.getDealAmount())) {
            throw new AmountException("Amount should be positive");
        }

        String currencyNotInTheList = validationService.checkCurrencyType(foreignExchangeTransactionDto);
        /**
         * if inserted currencies not real
         * */
        if (!currencyNotInTheList.isEmpty()) {
            throw new CurrencyNotFoundException(currencyNotInTheList);
        }

        /**
         * if transaction exist throw exception
         * */
        if(validationService.checkIfExist(foreignExchangeTransactionDto)){
            throw new TransactionExistException("this transaction already exist");
        }


        ForeignExchangeTransaction transactionEntity = mapForeignExchangeTransactionDtoToEntity(foreignExchangeTransactionDto);

        foreignExchangeTransactionRepository.save(transactionEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("transaction inserted successfully ");

    }

    /**
     * map the DTO accepted in the endpoint ton entity
     */
    private ForeignExchangeTransaction mapForeignExchangeTransactionDtoToEntity(ForeignExchangeTransactionDto dto) {
        ForeignExchangeTransaction foreignExchangeTransaction = new ForeignExchangeTransaction();

        foreignExchangeTransaction.setTransactionDate(dto.getTransactionDate());
        foreignExchangeTransaction.setDealAmount(dto.getDealAmount());
        foreignExchangeTransaction.setFromCurrencyCode(dto.getFromCurrencyCode());
        foreignExchangeTransaction.setToCurrencyCode(dto.getToCurrencyCode());

        return foreignExchangeTransaction;
    }

}

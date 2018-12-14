package com.mylearning.controllers;

import javax.validation.Valid;

import com.mylearning.exceptions.FieldNotParseableException;
import com.mylearning.exceptions.InvalidJsonException;
import com.mylearning.exceptions.OlderTransactionException;
import com.mylearning.services.TransactionService;
import com.mylearning.models.Transaction;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "Transaction Add Handler", notes = "Handle transactions and saves them")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Success"), @ApiResponse(code = 204, message = "If transaction is older than 60 seconds") })
    @PostMapping
    public ResponseEntity addTransaction(@RequestBody @Valid Transaction transaction) {
        LOGGER.info("Request addTransaction : {}", transaction);
        boolean result;
        try {
            result = transactionService.addTransaction(transaction);
        } catch (OlderTransactionException e) {
            LOGGER.error("Add transation failed for transaction : {}, with Exception {} and stacktrace {}", transaction, e.getMessage(), e.getStackTrace());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (FieldNotParseableException e) {
            LOGGER.error("Add transation failed for transaction : {}, with Exception {} and stacktrace {}", transaction, e.getMessage(), e.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (InvalidJsonException e){
            LOGGER.error("Add transation failed for transaction : {}, with Exception {} and stacktrace {}", transaction, e.getMessage(), e.getStackTrace());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        LOGGER.info("Result : {}", result);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @ApiOperation(value = "Transactions Delete Handler", notes = "Deletes all transactions")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Success")})
    @DeleteMapping
    public ResponseEntity deleteTransactions(){
        LOGGER.info("Request deleteTransactions");
        transactionService.deleteTransactions();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

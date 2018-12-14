package com.mylearning.services;

import com.mylearning.exceptions.FieldNotParseableException;
import com.mylearning.exceptions.InvalidJsonException;
import com.mylearning.exceptions.OlderTransactionException;
import com.mylearning.models.Transaction;
import com.mylearning.models.Statistic;
import org.springframework.stereotype.Component;

@Component
public interface TransactionService {

  boolean addTransaction(Transaction transaction) throws OlderTransactionException, InvalidJsonException,
          FieldNotParseableException;

  Statistic getStatistics();

  boolean deleteTransactions();

}

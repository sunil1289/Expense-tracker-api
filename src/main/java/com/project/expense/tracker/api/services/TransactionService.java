package com.project.expense.tracker.api.services;

import com.project.expense.tracker.api.domain.Transaction;
import com.project.expense.tracker.api.exception.EtBadRequestException;
import com.project.expense.tracker.api.exception.EtResourceNotFoundException;

import java.util.List;

public interface TransactionService {

    List<Transaction> fetchAllTransaction(Integer userId,Integer categoryId);

    Transaction fetchTransactionById(Integer userId,Integer categoryId,Integer transactionId) throws EtResourceNotFoundException;

    Transaction addTransaction(Integer userId,Integer categoryId,Double amount,String note,long transactionDate)
        throws EtBadRequestException;

    void updateTransaction(Integer userId ,Integer categoryId, Integer transactionId,Transaction transaction)
        throws  EtBadRequestException;


    void removeTransaction(Integer userId, Integer categoryId,Integer transactionId )
        throws EtResourceNotFoundException;


}

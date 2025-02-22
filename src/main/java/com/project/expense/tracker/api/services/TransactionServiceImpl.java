package com.project.expense.tracker.api.services;

import com.project.expense.tracker.api.domain.Transaction;
import com.project.expense.tracker.api.exception.EtBadRequestException;
import com.project.expense.tracker.api.exception.EtResourceNotFoundException;
import com.project.expense.tracker.api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;



    @Override
    public List<Transaction> fetchAllTransaction(Integer userId, Integer categoryId) {
        return transactionRepository.findAll(userId,categoryId);
    }

    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        return transactionRepository.findById(userId,categoryId,transactionId) ;
    }

    @Override
    public Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, long transactionDate) throws EtBadRequestException {
        int transactionId = transactionRepository.create(userId,categoryId,amount,note,transactionDate);
        return transactionRepository.findById(userId,categoryId,transactionId);
    }

    @Override
    public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException {
        transactionRepository.update(userId,categoryId,transactionId,transaction);
    }


    @Override
    public void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
transactionRepository.removeById(userId,categoryId,transactionId);
    }
}

package com.faf223.expensetrackerfaf.service;

import com.faf223.expensetrackerfaf.model.IMoneyTransaction;
import com.faf223.expensetrackerfaf.model.Income;
import com.faf223.expensetrackerfaf.model.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface ITransactionService {

    void createOrUpdate(IMoneyTransaction transaction);
    List<? extends IMoneyTransaction> getTransactions();
    List<? extends IMoneyTransaction> getTransactionsByEmail(String email);
    List<? extends IMoneyTransaction> getTransactionsByDate(LocalDate date);
    List<? extends IMoneyTransaction> getTransactionsByDate(LocalDate date, String email);
    List<? extends IMoneyTransaction> getTransactionsByMonth(Month month);
    List<? extends IMoneyTransaction> getTransactionsByMonth(Month month, String email);
    List<? extends IMoneyTransaction> getLastWeekTransactions();
    List<? extends IMoneyTransaction> getLastWeekTransactions(String email);
    List<? extends IMoneyTransaction> getLastMonthTransactions();
    List<? extends IMoneyTransaction> getLastMonthTransactions(String email);
    List<? extends IMoneyTransaction> getYearIntervalTransactions(int start, int end);
    List<? extends IMoneyTransaction> getYearIntervalTransactions(String email, int start, int end);
    IMoneyTransaction getTransactionById(long id);
    void deleteTransactionById(long it);
    boolean belongsToUser(IMoneyTransaction transaction);
}

package com.emretaskin.innova.service.interfaces;

import com.emretaskin.innova.dto.request.TransactionRequest;
import com.emretaskin.innova.dto.response.TransactionResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionResponse createTransaction(Long userId, TransactionRequest transactionRequest);

    List<TransactionResponse> getAllTransactionsByUserId(Long userId);

    TransactionResponse getTransactionById(Long userId, Long transactionId);

    void deleteTransaction(Long userId, Long transactionId);

    TransactionResponse updateTransaction(Long userId, Long transactionId, TransactionRequest transactionRequest);

    BigDecimal getTotalExpensesByUserId(Long userId);

    BigDecimal calculateTotalExpenseByDate(Long id, LocalDate date);

    BigDecimal calculateTotalExpenseByDateRange(Long id, LocalDate startDate, LocalDate endDate);
}

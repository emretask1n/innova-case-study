package com.emretaskin.innova.service.impl;

import com.emretaskin.innova.dto.request.TransactionRequest;
import com.emretaskin.innova.dto.response.TransactionResponse;
import com.emretaskin.innova.entity.Transaction;
import com.emretaskin.innova.entity.User;
import com.emretaskin.innova.exception.TransactionNotFoundException;
import com.emretaskin.innova.repository.TransactionRepository;
import com.emretaskin.innova.service.interfaces.TransactionService;
import com.emretaskin.innova.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;

    @Override
    @Transactional
    public TransactionResponse createTransaction(Long userId, TransactionRequest transactionRequest) {
        User user = getUserById(userId);

        Transaction transaction = buildTransaction(user, transactionRequest);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return mapTransactionToResponse(savedTransaction, user);
    }

    @Override
    public List<TransactionResponse> getAllTransactionsByUserId(Long userId) {
        User user = getUserById(userId);
        List<Transaction> transactions = transactionRepository.findByUser(user);
        return mapTransactionsToResponse(transactions, user);
    }

    @Override
    public TransactionResponse getTransactionById(Long userId, Long transactionId) {
        User user = getUserById(userId);
        Transaction transaction = getTransactionByIdAndUser(transactionId, user);

        return mapTransactionToResponse(transaction, user);
    }

    @Override
    public void deleteTransaction(Long userId, Long transactionId) {
        User user = getUserById(userId);
        Transaction transaction = getTransactionByIdAndUser(transactionId, user);

        transactionRepository.delete(transaction);
    }

    @Override
    public TransactionResponse updateTransaction(Long userId, Long transactionId, TransactionRequest transactionRequest) {
        User user = getUserById(userId);
        Transaction transaction = getTransactionByIdAndUser(transactionId, user);

        updateTransactionFields(transaction, transactionRequest);

        Transaction updatedTransaction = transactionRepository.save(transaction);

        return mapTransactionToResponse(updatedTransaction, user);
    }

    private User getUserById(Long userId) {
        return userService.findUserById(userId);
    }

    private Transaction buildTransaction(User user, TransactionRequest transactionRequest) {
        return Transaction.builder()
                .user(user)
                .amount(transactionRequest.getAmount())
                .category(transactionRequest.getCategory())
                .date(transactionRequest.getDate())
                .build();
    }

    private Transaction getTransactionByIdAndUser(Long transactionId, User user) {
        return transactionRepository.findByIdAndUser(transactionId, user)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + transactionId));
    }

    private void updateTransactionFields(Transaction transaction, TransactionRequest transactionRequest) {
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setCategory(transactionRequest.getCategory());
        transaction.setDate(transactionRequest.getDate());
    }

    private TransactionResponse mapTransactionToResponse(Transaction transaction, User user) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .category(transaction.getCategory())
                .date(transaction.getDate())
                .username(user.getUsername())
                .build();
    }
    private List<TransactionResponse> mapTransactionsToResponse(List<Transaction> transactions, User user) {
        return transactions.stream()
                .map(transaction -> mapTransactionToResponse(transaction, user))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalExpensesByUserId(Long userId) {
        User user = getUserById(userId);
        List<Transaction> transactions = transactionRepository.findByUser(user);

        BigDecimal totalExpenses = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            totalExpenses = totalExpenses.add(transaction.getAmount());
        }

        return totalExpenses;
    }

    @Override
    public BigDecimal calculateTotalExpenseByDate(Long userId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return transactionRepository.calculateTotalExpenseByDateRange(userId, startOfDay, endOfDay);
    }

    @Override
    public BigDecimal calculateTotalExpenseByDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startOfStartDate = startDate.atStartOfDay();
        LocalDateTime endOfEndDate = endDate.atTime(LocalTime.MAX);

        return transactionRepository.calculateTotalExpenseByDateRange(userId, startOfStartDate, endOfEndDate);
    }
}


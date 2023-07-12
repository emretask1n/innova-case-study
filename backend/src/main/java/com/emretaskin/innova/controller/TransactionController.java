package com.emretaskin.innova.controller;

import com.emretaskin.innova.dto.request.TransactionRequest;
import com.emretaskin.innova.dto.response.TransactionResponse;
import com.emretaskin.innova.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/{userId}/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(
            @PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByUserId(userId));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(
            @PathVariable Long userId,
            @PathVariable Long transactionId) {
        TransactionResponse transaction = transactionService.getTransactionById(userId, transactionId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @PathVariable Long userId,
            @RequestBody TransactionRequest transactionRequest) {
        TransactionResponse createdTransaction = transactionService.createTransaction(userId, transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(
            @PathVariable Long userId,
            @PathVariable Long transactionId) {
        transactionService.deleteTransaction(userId, transactionId);
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long userId,
            @PathVariable Long transactionId,
            @RequestBody TransactionRequest transactionRequest) {
        TransactionResponse updatedTransaction = transactionService.updateTransaction(userId, transactionId, transactionRequest);
        return ResponseEntity.ok(updatedTransaction);
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalExpensesByUserId(
            @PathVariable Long userId) {
        BigDecimal totalExpenses = transactionService.getTotalExpensesByUserId(userId);
        return ResponseEntity.ok(totalExpenses);
    }
    
}


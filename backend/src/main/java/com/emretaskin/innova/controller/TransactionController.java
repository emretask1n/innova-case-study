package com.emretaskin.innova.controller;

import com.emretaskin.innova.dto.request.TransactionRequest;
import com.emretaskin.innova.dto.response.TransactionResponse;
import com.emretaskin.innova.service.interfaces.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @Operation(summary = "Get all transactions for a user")
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(
            @PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByUserId(userId));
    }

    @Operation(summary = "Get a transaction by ID for a user")
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(
            @PathVariable Long userId,
            @PathVariable Long transactionId) {
        TransactionResponse transaction = transactionService.getTransactionById(userId, transactionId);
        return ResponseEntity.ok(transaction);
    }

    @Operation(summary = "Create a new transaction for a user")
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @PathVariable Long userId,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        TransactionResponse createdTransaction = transactionService.createTransaction(userId, transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }

    @Operation(summary = "Delete a transaction by ID for a user")
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(
            @PathVariable Long userId,
            @PathVariable Long transactionId) {
        transactionService.deleteTransaction(userId, transactionId);
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    @Operation(summary = "Update a transaction by ID for a user")
    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long userId,
            @PathVariable Long transactionId,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        TransactionResponse updatedTransaction = transactionService.updateTransaction(userId, transactionId, transactionRequest);
        return ResponseEntity.ok(updatedTransaction);
    }

    @Operation(summary = "Get the total expenses for a user")
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalExpensesByUserId(
            @PathVariable Long userId) {
        BigDecimal totalExpenses = transactionService.getTotalExpensesByUserId(userId);
        return ResponseEntity.ok(totalExpenses);
    }

}


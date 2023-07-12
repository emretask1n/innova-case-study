package com.emretaskin.innova.dto.request;

import com.emretaskin.innova.enums.TransactionCategory;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionRequest {
    private BigDecimal amount;
    private TransactionCategory category;
    private LocalDate date;
}

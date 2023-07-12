package com.emretaskin.innova.dto.response;

import com.emretaskin.innova.enums.TransactionCategory;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private TransactionCategory category;
    private LocalDate date;
    private String username;
}


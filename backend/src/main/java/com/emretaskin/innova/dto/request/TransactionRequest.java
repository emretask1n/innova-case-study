package com.emretaskin.innova.dto.request;

import com.emretaskin.innova.enums.TransactionCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionRequest {
    @NotBlank(message = "Amount cannot be blank")
    private BigDecimal amount;
    @NotBlank(message = "Category cannot be blank")
    private TransactionCategory category;
    @NotBlank(message = "Date cannot be blank")
    private LocalDate date;
}

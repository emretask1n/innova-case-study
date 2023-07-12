package com.emretaskin.innova.repository;

import com.emretaskin.innova.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

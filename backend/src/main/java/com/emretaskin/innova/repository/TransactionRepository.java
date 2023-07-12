package com.emretaskin.innova.repository;

import com.emretaskin.innova.entity.Transaction;
import com.emretaskin.innova.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);

    Optional<Transaction> findByIdAndUser(Long transactionId, User user);
}

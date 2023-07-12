package com.emretaskin.innova.repository;

import com.emretaskin.innova.entity.Transaction;
import com.emretaskin.innova.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);

    Optional<Transaction> findByIdAndUser(Long transactionId, User user);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = ?1 AND t.date >= ?2 AND t.date <= ?3")
    BigDecimal calculateTotalExpenseByDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

}

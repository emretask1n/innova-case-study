package com.emretaskin.innova.service.impl;

import com.emretaskin.innova.dto.response.TransactionResponse;
import com.emretaskin.innova.entity.User;
import com.emretaskin.innova.service.interfaces.TransactionService;
import com.emretaskin.innova.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionAggregationService {

    private final TransactionService transactionService;
    private final UserService userService;

    @Scheduled(cron = "0 0 0 * * *") // Günlük olarak çalışacak şekilde ayarlandı
    public void aggregateDailyExpenses() {
        List<User> users = userService.getAllUsers();

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        users.parallelStream().forEach(user -> {
            BigDecimal dailyTotalExpense = transactionService.calculateTotalExpenseByDate(user.getId(), today);

            System.out.println("User: " + user.getUsername() + ", Daily Total Expense: " + dailyTotalExpense);
        });
    }

    @Scheduled(cron = "0 0 0 * * MON") // Haftalık olarak pazartesi günü çalışacak şekilde ayarlandı
    public void aggregateWeeklyExpenses() {
        List<User> users = userService.getAllUsers();

        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minusWeeks(1);

        users.parallelStream().forEach(user -> {
            BigDecimal weeklyTotalExpense = transactionService.calculateTotalExpenseByDateRange(user.getId(), lastWeek, today);
            System.out.println("User: " + user.getUsername() + ", Weekly Total Expense: " + weeklyTotalExpense);
        });
    }

    @Scheduled(cron = "0 0 0 1 * *") // Aylık olarak ayın 1. günü çalışacak şekilde ayarlandı
    public void aggregateMonthlyExpenses() {
        List<User> users = userService.getAllUsers();

        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);

        users.parallelStream().forEach(user -> {
            BigDecimal monthlyTotalExpense = transactionService.calculateTotalExpenseByDateRange(user.getId(), lastMonth, today);
            System.out.println("User: " + user.getUsername() + ", Monthly Total Expense: " + monthlyTotalExpense);
        });
    }
}

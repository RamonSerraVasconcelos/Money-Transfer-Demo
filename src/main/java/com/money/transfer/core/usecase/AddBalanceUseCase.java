package com.money.transfer.core.usecase;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.FindAccountByUserIdBoundary;
import com.money.transfer.core.usecase.boundary.FindUserByIdBoundary;
import com.money.transfer.core.usecase.boundary.SaveAccountBoundary;
import com.money.transfer.exception.ResourceNotFoundException;
import com.money.transfer.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AddBalanceUseCase {

    private final FindUserByIdBoundary findUserByIdBoundary;

    private final FindAccountByUserIdBoundary findAccountByUserIdBoundary;

    private final SaveAccountBoundary saveAccountBoundary;

    public void addBalance(String userId, BigDecimal amount) {
        User user = findUserByIdBoundary.findUser(userId).orElseThrow(() -> new ResourceViolationException("Invalid user"));

        Account account = findAccountByUserIdBoundary.findAccount(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User does not have an account"));

        int comparisonResult = amount.compareTo(BigDecimal.ZERO);

        if (comparisonResult < 0 || comparisonResult == 0) {
            throw new ResourceViolationException("Deposit amount must be greater than 0");
        }

        account.setBalance(account.getBalance().add(amount));

        saveAccountBoundary.saveAccount(account);
    }
}

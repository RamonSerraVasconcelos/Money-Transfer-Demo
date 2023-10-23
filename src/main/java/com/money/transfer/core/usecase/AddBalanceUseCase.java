package com.money.transfer.core.usecase;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.usecase.boundary.FindAccountBoundary;
import com.money.transfer.core.usecase.boundary.SaveAccountBoundary;
import com.money.transfer.exception.ResourceNotFoundException;
import com.money.transfer.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AddBalanceUseCase {

    private final FindAccountBoundary findAccountBoundary;

    private final SaveAccountBoundary saveAccountBoundary;

    public void addBalance(Integer agency, Integer accountNumber, BigDecimal amount) {
        Account account = findAccountBoundary.findAccount(agency, accountNumber).orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        int comparisonResult = amount.compareTo(BigDecimal.ZERO);

        if (comparisonResult < 0 || comparisonResult == 0) {
            throw new ResourceViolationException("Deposit amount must be greater than 0");
        }

        account.setBalance(account.getBalance().add(amount));

        saveAccountBoundary.saveAccount(account);
    }
}

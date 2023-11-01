package com.money.transfer.core.usecase;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.FindAccountBoundary;
import com.money.transfer.core.usecase.boundary.FindAccountByCnpjBoundary;
import com.money.transfer.core.usecase.boundary.FindAccountByUserIdBoundary;
import com.money.transfer.core.usecase.boundary.FindUserByIdBoundary;
import com.money.transfer.core.usecase.boundary.SaveAccountBoundary;
import com.money.transfer.exception.ResourceConflictException;
import com.money.transfer.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class CreateAccountUseCase {

    private final FindUserByIdBoundary findUserByIdBoundary;

    private final FindAccountByUserIdBoundary findAccountByUserIdBoundary;

    private final FindAccountBoundary findAccountBoundary;

    private final FindAccountByCnpjBoundary findAccountByCnpjBoundary;

    private final SaveAccountBoundary saveAccountBoundary;

    private final Integer agency = generateRandomNumber(4);

    public Account createAccount(String userId, String cnpj) {
        User user = findUserByIdBoundary.findUser(userId).orElseThrow(() -> new ResourceViolationException("Invalid user"));

        Optional<Account> optionalAccount = findAccountByUserIdBoundary.findAccount(userId);

        if(optionalAccount.isPresent()) {
            throw new ResourceViolationException("User already has an account");
        }

        Optional<Account> optionalAccountByCnpj = findAccountByCnpjBoundary.findAccount(cnpj);

        if(optionalAccountByCnpj.isPresent()) {
            throw new ResourceConflictException("Cnpj is already registered");
        }

        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .agency(agency)
                .number(generateAccountNumber())
                .cnpj(nonNull(cnpj) ? cnpj : null)
                .balance(BigDecimal.ZERO)
                .build();

        return saveAccountBoundary.saveAccount(account);
    }

    private Integer generateAccountNumber() {
        Integer accountNumber;

        do {
            accountNumber = generateRandomNumber(5);
        } while (findAccountBoundary.findAccount(agency, accountNumber).isPresent());

        return accountNumber;
    }

    private Integer generateRandomNumber(Integer size) {
        int min = (int) Math.pow(10, size - 1);
        int max = (int) Math.pow(10, size) - 1;

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

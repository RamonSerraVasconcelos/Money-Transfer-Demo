package com.money.transfer.core.usecase;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.FindAccountByAgencyBoundary;
import com.money.transfer.core.usecase.boundary.FindAccountByNumberBoundary;
import com.money.transfer.core.usecase.boundary.FindAccountByUserIdBoundary;
import com.money.transfer.core.usecase.boundary.FindUserByIdBoundary;
import com.money.transfer.core.usecase.boundary.SaveAccountBoundary;
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

    private final FindAccountByAgencyBoundary findAccountByAgencyBoundary;

    private final FindAccountByNumberBoundary findAccountByNumberBoundary;

    private final SaveAccountBoundary saveAccountBoundary;

    public Account createAccount(String userId, String cnpj) {
        Optional<User> user = findUserByIdBoundary.findUser(userId);

        if (user.isEmpty()) {
            throw new ResourceViolationException("Invalid user");
        }

        Optional<Account> optionalAccount = findAccountByUserIdBoundary.findAccount(userId);

        if(optionalAccount.isPresent()) {
            throw new ResourceViolationException("User already has an account");
        }

        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .user(user.get())
                .agency(generateAgencyNumber())
                .number(generateAccountNumber())
                .cnpj(nonNull(cnpj) ? cnpj : null)
                .balance(BigDecimal.ZERO)
                .build();

        return saveAccountBoundary.saveAccount(account);
    }

    private Integer generateAgencyNumber() {
        Integer accountAgency;

        do {
            accountAgency = generateRandomNumber(4);
        } while (findAccountByAgencyBoundary.findAccount(accountAgency).isPresent());

        return accountAgency;
    }

    private Integer generateAccountNumber() {
        Integer accountNumber;

        do {
            accountNumber = generateRandomNumber(5);
        } while (findAccountByNumberBoundary.findAccount(accountNumber).isPresent());

        return accountNumber;
    }

    private Integer generateRandomNumber(Integer size) {
        int min = (int) Math.pow(10, size - 1);
        int max = (int) Math.pow(10, size) - 1;

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.usecase.boundary.FindAccountByUserIdBoundary;
import com.money.transfer.dataprovider.database.entity.AccountEntity;
import com.money.transfer.dataprovider.database.mapper.AccountEntityMapper;
import com.money.transfer.dataprovider.database.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAccountByUserIdGateway implements FindAccountByUserIdBoundary {

    private final AccountRepository accountRepository;

    public Optional<Account> findAccount(String userId) {
        AccountEntity accountEntity = accountRepository.findByUserId(userId);

        return Optional.ofNullable(accountEntity).map(AccountEntityMapper::accountEntityToAccount);
    }
}

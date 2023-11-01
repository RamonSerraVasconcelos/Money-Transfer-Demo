package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.usecase.boundary.FindAccountByCnpjBoundary;
import com.money.transfer.dataprovider.database.entity.AccountEntity;
import com.money.transfer.dataprovider.database.mapper.AccountEntityMapper;
import com.money.transfer.dataprovider.database.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAccountByCnpjGateway implements FindAccountByCnpjBoundary {

    private final AccountRepository accountRepository;

    public Optional<Account> findAccount(String cnpj) {
        AccountEntity accountEntity = accountRepository.findByCnpj(cnpj);

        return Optional.ofNullable(accountEntity).map(AccountEntityMapper::accountEntityToAccount);
    }
}

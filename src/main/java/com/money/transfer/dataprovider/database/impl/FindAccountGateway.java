package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.usecase.boundary.FindAccountBoundary;
import com.money.transfer.dataprovider.database.entity.AccountEntity;
import com.money.transfer.dataprovider.database.mapper.AccountEntityMapper;
import com.money.transfer.dataprovider.database.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAccountGateway implements FindAccountBoundary {

    private final AccountRepository accountRepository;

    public Optional<Account> findAccount(Integer agency, Integer number) {
        AccountEntity accountEntity = accountRepository.findByAgencyAndNumber(agency, number);

        return Optional.ofNullable(accountEntity).map(AccountEntityMapper::accountEntityToAccount);
    }
}

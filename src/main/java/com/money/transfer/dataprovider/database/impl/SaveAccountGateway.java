package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.usecase.boundary.SaveAccountBoundary;
import com.money.transfer.dataprovider.database.entity.AccountEntity;
import com.money.transfer.dataprovider.database.mapper.AccountEntityMapper;
import com.money.transfer.dataprovider.database.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveAccountGateway implements SaveAccountBoundary {

    private final AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        AccountEntity accountEntity = AccountEntityMapper.accountToAccountEntity(account);

        AccountEntity savedAccount = accountRepository.save(accountEntity);

        return AccountEntityMapper.accountEntityToAccount(savedAccount);
    }
}

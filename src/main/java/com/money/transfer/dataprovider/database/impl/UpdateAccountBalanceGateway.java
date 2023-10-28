package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.usecase.boundary.UpdateAccountBalanceBoundary;
import com.money.transfer.dataprovider.database.entity.AccountEntity;
import com.money.transfer.dataprovider.database.mapper.AccountEntityMapper;
import com.money.transfer.dataprovider.database.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdateAccountBalanceGateway implements UpdateAccountBalanceBoundary {

    private final AccountRepository accountRepository;

    public void updateBalance(Account account) {
        AccountEntity accountEntity = AccountEntityMapper.accountToAccountEntity(account);

        accountRepository.save(accountEntity);
    }
}

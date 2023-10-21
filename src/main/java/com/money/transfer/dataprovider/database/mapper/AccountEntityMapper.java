package com.money.transfer.dataprovider.database.mapper;

import com.money.transfer.core.model.Account;
import com.money.transfer.dataprovider.database.entity.AccountEntity;

public class AccountEntityMapper {

    public static AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .user(UserEntityMapper.userToUserEntity(account.getUser()))
                .agency(account.getAgency())
                .number(account.getNumber())
                .cnpj(account.getCnpj())
                .balance(account.getBalance())
                .build();
    }

    public static Account accountEntityToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .user(UserEntityMapper.userEntityToUser(accountEntity.getUser()))
                .agency(accountEntity.getAgency())
                .number(accountEntity.getNumber())
                .cnpj(accountEntity.getCnpj())
                .balance(accountEntity.getBalance())
                .build();
    }
}

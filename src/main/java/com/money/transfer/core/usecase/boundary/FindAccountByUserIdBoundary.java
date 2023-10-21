package com.money.transfer.core.usecase.boundary;

import com.money.transfer.core.model.Account;

import java.util.Optional;

public interface FindAccountByUserIdBoundary {

    Optional<Account> findAccount(String userId);
}

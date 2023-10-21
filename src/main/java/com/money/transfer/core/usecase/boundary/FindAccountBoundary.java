package com.money.transfer.core.usecase.boundary;

import com.money.transfer.core.model.Account;

import java.util.Optional;

public interface FindAccountBoundary {

    Optional<Account> findAccount(Integer agency, Integer number);
}

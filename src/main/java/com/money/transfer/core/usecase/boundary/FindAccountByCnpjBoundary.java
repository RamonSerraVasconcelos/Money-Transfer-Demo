package com.money.transfer.core.usecase.boundary;

import com.money.transfer.core.model.Account;

import java.util.Optional;

public interface FindAccountByCnpjBoundary {

    Optional<Account> findAccount(String cnpj);
}

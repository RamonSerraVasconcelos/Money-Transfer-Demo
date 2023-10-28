package com.money.transfer.core.usecase.boundary;

import com.money.transfer.core.model.Account;

public interface UpdateAccountBalanceBoundary {

    void updateBalance(Account account);
}

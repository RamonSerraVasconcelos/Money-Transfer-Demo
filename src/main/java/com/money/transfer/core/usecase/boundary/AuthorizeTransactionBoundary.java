package com.money.transfer.core.usecase.boundary;

import com.money.transfer.core.model.TransferAuthorization;

public interface AuthorizeTransactionBoundary {

    TransferAuthorization authorizeTransaction(String userCpf);
}

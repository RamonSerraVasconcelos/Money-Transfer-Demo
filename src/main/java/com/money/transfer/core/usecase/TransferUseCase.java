package com.money.transfer.core.usecase;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.model.TransferAuthorization;
import com.money.transfer.core.model.enums.AuthorizationStatusEnum;
import com.money.transfer.core.usecase.boundary.AuthorizeTransactionBoundary;
import com.money.transfer.core.usecase.boundary.FindAccountBoundary;
import com.money.transfer.core.usecase.boundary.FindAccountByUserIdBoundary;
import com.money.transfer.core.usecase.boundary.FindUserByIdBoundary;
import com.money.transfer.core.usecase.boundary.NotifyTransferReceiverBoundary;
import com.money.transfer.core.usecase.boundary.UpdateAccountBalanceBoundary;
import com.money.transfer.exception.ForbiddenException;
import com.money.transfer.exception.InsufficientFundsException;
import com.money.transfer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransferUseCase {

    private final FindAccountByUserIdBoundary findAccountByUserIdBoundary;

    private final FindAccountBoundary findAccountBoundary;

    private final AuthorizeTransactionBoundary authorizeTransactionBoundary;

    private final UpdateAccountBalanceBoundary updateAccountBalanceBoundary;

    private final NotifyTransferReceiverBoundary notifyTransferReceiverBoundary;

    private final FindUserByIdBoundary findUserByIdBoundary;

    public void transfer(String sourceUserId, Integer accountAgency, Integer accountNumber, BigDecimal amount) {
        try {
            Account sourceAccount = findAccountByUserIdBoundary.findAccount(sourceUserId).orElseThrow(() -> new ResourceNotFoundException("Error recovering user account"));

            Account targetAccount = findAccountBoundary.findAccount(accountAgency, accountNumber).orElseThrow(() -> new ResourceNotFoundException("Invalid account"));

            if (sourceAccount.isAccountPJ()) {
                throw new ForbiddenException("PJ Accounts are not authorized to perform this action");
            }

            if (sourceAccount.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient balance");
            }

            TransferAuthorization transferAuthorization = authorizeTransactionBoundary.authorizeTransaction(sourceUserId);

            if (!transferAuthorization.getStatus().equals(AuthorizationStatusEnum.AUTHORIZED)) {
                throw new ForbiddenException("The transaction was not authorized by the authorization entity");
            }

            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            targetAccount.setBalance(targetAccount.getBalance().add(amount));

            updateAccountBalanceBoundary.updateBalance(sourceAccount);
            updateAccountBalanceBoundary.updateBalance(targetAccount);

            notifyTransferReceiverBoundary.notifyUser(targetAccount.getUser().getId(), amount);
        } catch (Exception e) {
            log.error("Error completing transfer", e);
            throw e;
        }
    }
}

package com.money.transfer.core.usecase.boundary;

import java.math.BigDecimal;

public interface NotifyTransferReceiverBoundary {

    void notifyUser(String userEmail, BigDecimal amountReceived);
}

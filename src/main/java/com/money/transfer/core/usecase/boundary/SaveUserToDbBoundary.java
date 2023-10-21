package com.money.transfer.core.usecase.boundary;

import com.money.transfer.core.model.User;

public interface SaveUserToDbBoundary {

    User save(User user);
}

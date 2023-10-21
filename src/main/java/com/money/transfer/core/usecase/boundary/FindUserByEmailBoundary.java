package com.money.transfer.core.usecase.boundary;

import com.money.transfer.core.model.User;

import java.util.Optional;

public interface FindUserByEmailBoundary {

    Optional<User> findUser(String email);
}

package com.money.transfer.core.usecase.boundary;

import com.money.transfer.core.model.User;

import java.util.List;

public interface FindAllUsersBoundary {

    List<User> findUsers();
}

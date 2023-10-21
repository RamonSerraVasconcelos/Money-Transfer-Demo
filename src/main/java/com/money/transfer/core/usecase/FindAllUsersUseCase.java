package com.money.transfer.core.usecase;

import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.FindAllUsersBoundary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllUsersUseCase {

    private final FindAllUsersBoundary findAllUsersBoundary;

    public List<User> findUsers() {
        return findAllUsersBoundary.findUsers();
    }
}

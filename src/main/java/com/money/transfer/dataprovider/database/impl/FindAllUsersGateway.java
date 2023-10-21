package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.FindAllUsersBoundary;
import com.money.transfer.dataprovider.database.mapper.UserEntityMapper;
import com.money.transfer.dataprovider.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllUsersGateway implements FindAllUsersBoundary {

    private final UserRepository userRepository;

    public List<User> findUsers() {
        return userRepository.findAllByOrderByIdAsc().stream().map(UserEntityMapper::userEntityToUser).collect(Collectors.toList());
    }
}

package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.FindUserByIdBoundary;
import com.money.transfer.dataprovider.database.entity.UserEntity;
import com.money.transfer.dataprovider.database.mapper.UserEntityMapper;
import com.money.transfer.dataprovider.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindUserByIdGateway implements FindUserByIdBoundary {

    private final UserRepository userRepository;

    public Optional<User> findUser(String id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        return userEntity.map(UserEntityMapper::userEntityToUser);
    }
}

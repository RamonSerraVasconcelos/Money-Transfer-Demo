package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.FindUserByEmailBoundary;
import com.money.transfer.dataprovider.database.entity.UserEntity;
import com.money.transfer.dataprovider.database.mapper.UserEntityMapper;
import com.money.transfer.dataprovider.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindUserByEmailGateway implements FindUserByEmailBoundary {

    private final UserRepository userRepository;

    public Optional<User> findUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        return Optional.ofNullable(userEntity).map(UserEntityMapper::userEntityToUser);
    }
}

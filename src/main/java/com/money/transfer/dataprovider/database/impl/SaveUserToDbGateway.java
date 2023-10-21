package com.money.transfer.dataprovider.database.impl;

import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.SaveUserToDbBoundary;
import com.money.transfer.dataprovider.database.entity.UserEntity;
import com.money.transfer.dataprovider.database.mapper.UserEntityMapper;
import com.money.transfer.dataprovider.database.repository.UserRepository;
import com.money.transfer.exception.ResourceConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveUserToDbGateway implements SaveUserToDbBoundary {

    private final UserRepository userRepository;

    public User save(User user) {
        try {
            UserEntity userEntity = UserEntityMapper.userToUserEntity(user);

            UserEntity savedUser = userRepository.save(userEntity);

            return UserEntityMapper.userEntityToUser(savedUser);
        } catch (DataIntegrityViolationException e) {
            log.error("Error when saving user on database", e);
            throw new ResourceConflictException("User's email and cpf must unique");
        }
    }
}

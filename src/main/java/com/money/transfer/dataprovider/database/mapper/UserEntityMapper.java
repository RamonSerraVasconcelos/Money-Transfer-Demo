package com.money.transfer.dataprovider.database.mapper;

import com.money.transfer.dataprovider.database.entity.UserEntity;
import com.money.transfer.core.model.User;

public class UserEntityMapper {

    public static UserEntity userToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .password(user.getPassword())
                .build();
    }


    public static User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .cpf(userEntity.getCpf())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }
}

package com.money.transfer.entrypoint.mapper;

import com.money.transfer.core.model.User;
import com.money.transfer.entrypoint.dto.UserResponseDto;

public class UserDtoMapper {

    public static UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .build();
    }
}

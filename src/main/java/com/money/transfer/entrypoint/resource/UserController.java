package com.money.transfer.entrypoint.resource;

import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.CreateUserUseCase;
import com.money.transfer.core.usecase.FindAllUsersUseCase;
import com.money.transfer.entrypoint.dto.UserRequestDto;
import com.money.transfer.entrypoint.dto.UserResponseDto;
import com.money.transfer.entrypoint.mapper.UserDtoMapper;
import com.money.transfer.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    private final FindAllUsersUseCase findAllUsersUseCase;

    private final Validator validator;

    @PostMapping
    public UserResponseDto registerUser(@RequestBody UserRequestDto userRequestDto) {
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        User createdUser = createUserUseCase.createUser(userRequestDto.getEmail(),
                userRequestDto.getName(), userRequestDto.getCpf(), userRequestDto.getPassword(), userRequestDto.getConfirmationPassword());

        return UserDtoMapper.userToUserResponseDto(createdUser);
    }

    @GetMapping
    public List<UserResponseDto> loadAll() {
        return findAllUsersUseCase.findUsers().stream().map(UserDtoMapper::userToUserResponseDto).collect(Collectors.toList());
    }
}

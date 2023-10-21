package com.money.transfer.entrypoint.resource;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.usecase.CreateAccountUseCase;
import com.money.transfer.entrypoint.dto.AccountRequestDto;
import com.money.transfer.entrypoint.dto.AccountResponseDto;
import com.money.transfer.entrypoint.mapper.AccountDtoMapper;
import com.money.transfer.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping(path = "/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    private final Validator validator;

    @PostMapping
    public AccountResponseDto createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(accountRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Account createdAccount = createAccountUseCase.createAccount(accountRequestDto.getUserId(), accountRequestDto.getCnpj());

        return AccountDtoMapper.accountToAccountResponseDto(createdAccount);
    }
}

package com.money.transfer.entrypoint.resource;

import com.money.transfer.core.model.Account;
import com.money.transfer.core.usecase.AddBalanceUseCase;
import com.money.transfer.core.usecase.CreateAccountUseCase;
import com.money.transfer.core.usecase.TransferUseCase;
import com.money.transfer.entrypoint.dto.AccountRequestDto;
import com.money.transfer.entrypoint.dto.AccountResponseDto;
import com.money.transfer.entrypoint.dto.DepositRequestDto;
import com.money.transfer.entrypoint.dto.TransferRequestDto;
import com.money.transfer.entrypoint.mapper.AccountDtoMapper;
import com.money.transfer.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final AddBalanceUseCase addBalanceUseCase;

    private final TransferUseCase transferUseCase;

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

    @PostMapping("/deposit")
    public ResponseEntity<Void> addBalance(@RequestBody DepositRequestDto depositRequestDto) {
       Set<ConstraintViolation<DepositRequestDto>> violations = validator.validate(depositRequestDto);
       if (!violations.isEmpty()) {
           throw new ResourceViolationException(violations);
       }

       addBalanceUseCase.addBalance(depositRequestDto.getAgency(), depositRequestDto.getAccountNumber(), depositRequestDto.getAmount());

       return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequestDto transferRequestDto) {
        Set<ConstraintViolation<TransferRequestDto>> violations = validator.validate(transferRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        transferUseCase.transfer(transferRequestDto.getPayerId(), transferRequestDto.getAgency(), transferRequestDto.getAccountNumber(), transferRequestDto.getAmount());

        return ResponseEntity.ok().build();
    }
}

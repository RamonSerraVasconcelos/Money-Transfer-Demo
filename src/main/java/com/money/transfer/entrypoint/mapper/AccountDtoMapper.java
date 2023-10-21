package com.money.transfer.entrypoint.mapper;

import com.money.transfer.core.model.Account;
import com.money.transfer.entrypoint.dto.AccountResponseDto;

public class AccountDtoMapper {

    public static AccountResponseDto accountToAccountResponseDto(Account account) {
        return AccountResponseDto.builder()
                .userId(account.getUser().getId())
                .agency(account.getAgency())
                .number(account.getNumber())
                .cnpj(account.getCnpj())
                .balance(account.getBalance())
                .build();
    }
}

package com.money.transfer.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountResponseDto {

    private String userId;
    private Integer agency;
    private Integer number;
    private String cnpj;
    private BigDecimal balance;
}

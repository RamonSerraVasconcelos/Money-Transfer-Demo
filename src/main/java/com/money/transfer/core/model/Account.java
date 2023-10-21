package com.money.transfer.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    private String id;
    private User user;
    private Integer agency;
    private Integer number;
    private String cnpj;
    private BigDecimal balance;
}

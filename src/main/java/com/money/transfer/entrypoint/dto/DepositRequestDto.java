package com.money.transfer.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DepositRequestDto {

    @NotNull(message = "Agency is required")
    @Min(value = 4, message = "Invalid agency")
    @Max(value = 9999, message = "Invalid agency")
    private Integer agency;

    @NotNull(message = "Account number is required")
    @Min(value = 1, message = "Invalid account number")
    @Max(value = 99999, message = "Invalid account number")
    private Integer accountNumber;

    @NotNull(message = "Deposit amount is required")
    @DecimalMin(value = "1.00", inclusive = true, message = "Money amount must be greater than 0")
    private BigDecimal amount;
}

package com.money.transfer.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transfer {

    private String id;
    private String from;
    private String to;
    private BigDecimal amount;
    private String status;
    private LocalDateTime transferDate;
}

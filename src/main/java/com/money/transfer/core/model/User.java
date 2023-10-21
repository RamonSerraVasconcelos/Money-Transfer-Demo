package com.money.transfer.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private String id;
    private String name;
    private String email;
    private String cpf;
    private String password;
    private LocalDateTime createdAt;
}

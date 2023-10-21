package com.money.transfer.dataprovider.database.entity;

import com.money.transfer.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "agencies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountEntity {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Integer agency;

    private Integer number;

    private String cnpj;

    private BigDecimal balance;
}

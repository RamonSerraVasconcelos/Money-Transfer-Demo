package com.money.transfer.dataprovider.database.repository;

import com.money.transfer.dataprovider.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    AccountEntity findByAgency(Integer agencyNumber);

    AccountEntity findByNumber(Integer number);

    AccountEntity findByUserId(String userId);
}

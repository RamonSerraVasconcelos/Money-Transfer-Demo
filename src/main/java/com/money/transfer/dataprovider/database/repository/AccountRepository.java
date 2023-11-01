package com.money.transfer.dataprovider.database.repository;

import com.money.transfer.dataprovider.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    AccountEntity findByAgencyAndNumber(Integer agency, Integer number);

    AccountEntity findByUserId(String userId);

    AccountEntity findByCnpj(String cnpj);
}

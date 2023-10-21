package com.money.transfer.dataprovider.database.repository;

import com.money.transfer.dataprovider.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    List<UserEntity> findAllByOrderByIdAsc();
}

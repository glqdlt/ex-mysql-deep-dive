package com.glqdlt.ex.exmysqldeepdive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface CustomerEntityRepo extends JpaRepository<CustomerEntity, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CustomerEntity> findByAddressIdIs(Integer addressId);

    Optional<CustomerEntity> findByAddressId(Integer addressId);
}

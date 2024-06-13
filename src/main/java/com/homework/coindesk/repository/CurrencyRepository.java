package com.homework.coindesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homework.coindesk.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String>{
    Optional<CurrencyEntity> findByCodeAndActive(String code, boolean active);

    List<CurrencyEntity> findAllByActive(boolean active);
}

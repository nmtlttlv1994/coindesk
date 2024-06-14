package com.homework.coindesk.repository;

import com.homework.coindesk.CurrencyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.homework.coindesk.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String>{

    Optional<CurrencyEntity> findByCodeAndActive(String code, boolean active);

    @Query(value = "SELECT * FROM CURRENCY C LEFT JOIN SYSTEM_CONFIG S " +
            "ON C.CODE = S.CURRENCY_CODE " +
            "WHERE C.ACTIVE = :active " +
            "AND C.CODE = :code " +
            "AND S.LANGUAGE_CODE = :language ", nativeQuery = true)
    Optional<CurrencyProjection> findByCodeAndActiveAndLanguage(String code, String language, boolean active);

    List<CurrencyEntity> findAllByActive(boolean active);
}

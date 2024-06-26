package com.homework.coindesk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "currency", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class CurrencyEntity {

    @Id
    private String code;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "rate")
    private String rate;

    @Column(name = "description")
    private String description;

    @Column(name = "rate_float")
    private Float rateFloat;

    @Column(name = "active", nullable = false)
    public Boolean active;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    public LocalDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    public LocalDateTime updatedDate;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<SystemConfigEntity> systemConfigs = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(this.createdDate)) {
            this.createdDate = LocalDateTime.now();
        }
        if (Objects.isNull(this.updatedDate)) {
            this.updatedDate = LocalDateTime.now();
        }
        if (Objects.isNull(this.active)) {
            this.active = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}

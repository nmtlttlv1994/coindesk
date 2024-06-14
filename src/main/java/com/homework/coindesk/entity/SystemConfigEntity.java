package com.homework.coindesk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "system_config")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class SystemConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "active", nullable = false)
    public Boolean active;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    public LocalDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    public LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "currency_code", referencedColumnName = "code")
    private CurrencyEntity currency;

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

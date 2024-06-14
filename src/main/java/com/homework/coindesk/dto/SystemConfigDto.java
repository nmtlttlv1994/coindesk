package com.homework.coindesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.homework.coindesk.util.deserialize.TimestampToDate;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemConfigDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String languageCode;

    private String currencyCode;

    @JsonIgnore
    private Boolean active = true;

    @JsonDeserialize(using = TimestampToDate.class)
    private LocalDateTime createdDate;

    @JsonDeserialize(using = TimestampToDate.class)
    private LocalDateTime updatedDate;
}

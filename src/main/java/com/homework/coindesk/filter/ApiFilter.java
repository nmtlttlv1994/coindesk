package com.homework.coindesk.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Log4j2
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class ApiFilter<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T criteria;
}
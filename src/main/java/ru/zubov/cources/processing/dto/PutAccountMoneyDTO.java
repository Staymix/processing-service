package ru.zubov.cources.processing.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PutAccountMoneyDTO {

    @JsonAlias("uid")
    private String uid;

    @JsonAlias("id")
    private long accountId;

    @JsonAlias("amount")
    private BigDecimal money;
}

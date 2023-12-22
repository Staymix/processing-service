package ru.zubov.cources.processing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @SequenceGenerator(name = "account_generator", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "account_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long useId;

    @Column(name = "CURRENCY_CODE", length = 3, nullable = false)
    private String currencyCode;

    @Column(name = "BALANCE", length = 3, nullable = false)
    private BigDecimal balance;
}

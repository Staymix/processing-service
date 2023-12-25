package ru.zubov.cources.processing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class JournalOperationId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;

    @Column(name = "ACCOUNT_ID")
    private long id;
}

package ru.zubov.cources.processing.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "JOURNAL_OPERATION")
public class JournalOperation {

    @EmbeddedId
    private JournalOperationId journalOperationId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;
}

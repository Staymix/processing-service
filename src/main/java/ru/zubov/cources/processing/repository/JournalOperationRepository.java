package ru.zubov.cources.processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubov.cources.processing.model.JournalOperation;
import ru.zubov.cources.processing.model.JournalOperationId;

import java.util.Optional;

public interface JournalOperationRepository extends JpaRepository<JournalOperation, JournalOperationId> {
    Optional<JournalOperation> findByJournalOperationId(JournalOperationId journalOperationId);
}

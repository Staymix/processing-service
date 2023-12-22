package ru.zubov.cources.processing.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zubov.cources.processing.model.AccountEntity;

import java.util.List;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    List<AccountEntity> findByUseId(Long userId);
}

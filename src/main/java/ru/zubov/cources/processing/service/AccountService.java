package ru.zubov.cources.processing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.zubov.cources.processing.dto.NewAccountDTO;
import ru.zubov.cources.processing.model.AccountEntity;
import ru.zubov.cources.processing.model.JournalOperation;
import ru.zubov.cources.processing.model.JournalOperationId;
import ru.zubov.cources.processing.repository.AccountRepository;
import ru.zubov.cources.processing.repository.JournalOperationRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final JournalOperationRepository operationRepository;

    @Transactional
    public AccountEntity createNewAccount(NewAccountDTO dto) {
        AccountEntity account = new AccountEntity();
        account.setCurrencyCode(dto.getCurrencyCode());
        account.setUseId(dto.getUserId());
        account.setBalance(new BigDecimal(0));

        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public AccountEntity addMoneyToAccount(String uid, Long id, BigDecimal money) {
        checkOperation(uid, id);
        Optional<AccountEntity> account = accountRepository.findById(id);
        return account.map(acc -> {
            BigDecimal balance = acc.getBalance().add(money);
            acc.setBalance(balance);
            return accountRepository.save(acc);
        }).orElseThrow(() -> new IllegalArgumentException("Account with ID " + id + " not found"));
    }

    private void checkOperation(String uid, Long id) {
        Optional<JournalOperation> operation = operationRepository.findByJournalOperationId(new JournalOperationId(uid, id));
        if (operation.isPresent()) {
            throw new IllegalArgumentException("Operation was attempted to execute again for UID");
        }
        AccountEntity account = getAccountById(id);
        JournalOperation operation1 = new JournalOperation(new JournalOperationId(uid, id), account.getUseId(), LocalDateTime.now());
        operationRepository.save(operation1);
    }

    public AccountEntity getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Account with ID " + id + " not found"));
    }

    public List<AccountEntity> getAllAccount(Long userId) {
        return accountRepository.findByUseId(userId);
    }
}

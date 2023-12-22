package ru.zubov.cources.processing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.zubov.cources.processing.dto.NewAccountDTO;
import ru.zubov.cources.processing.model.AccountEntity;
import ru.zubov.cources.processing.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    @Transactional
    public AccountEntity createNewAccount(NewAccountDTO dto) {
        AccountEntity account = new AccountEntity();
        account.setCurrencyCode(dto.getCurrencyCode());
        account.setUseId(dto.getUserId());
        account.setBalance(new BigDecimal(0));

        return repository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public AccountEntity addMoneyToAccount(String uid, Long id, BigDecimal money) {
        Optional<AccountEntity> account = repository.findById(id);
        return account.map(acc -> {
            BigDecimal balance = acc.getBalance().add(money);
            acc.setBalance(balance);
            return repository.save(acc);
        }).orElseThrow(() -> new IllegalArgumentException("Account with ID " + id + " not found"));
    }

    public AccountEntity getAccountById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Account with ID " + id + " not found"));
    }

    public List<AccountEntity> getAllAccount(Long userId) {
        return repository.findByUseId(userId);
    }
}

package ru.zubov.cources.processing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.zubov.cources.processing.dto.ExchangeMoneyDTO;
import ru.zubov.cources.processing.dto.NewAccountDTO;
import ru.zubov.cources.processing.dto.PutAccountMoneyDTO;
import ru.zubov.cources.processing.model.AccountEntity;
import ru.zubov.cources.processing.service.AccountService;
import ru.zubov.cources.processing.service.ExchangeService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "processing")
@RequiredArgsConstructor
public class ProcessingController {

    private final AccountService accountService;
    private final ExchangeService exchangeService;

    @PostMapping("/account")
    public AccountEntity createAccount(@RequestBody NewAccountDTO account) {
        return accountService.createNewAccount(account);
    }

    @PutMapping("/account/{id}")
    public AccountEntity putMoney(@PathVariable Long id, @RequestBody PutAccountMoneyDTO data) {
        return accountService.addMoneyToAccount(data.getUid(), id, data.getMoney());
    }

    @PutMapping("/exchange/{uid}")
    public BigDecimal exchange(@PathVariable String uid, @RequestBody ExchangeMoneyDTO data) {
        return exchangeService.exchangeCurrency(uid, data.getFromAccountId(), data.getToAccountId(), data.getMoney());
    }

    @GetMapping("accounts/{id}")
    public List<AccountEntity> getAllAccount(@PathVariable Long id) {
        return accountService.getAllAccount(id);
    }
}

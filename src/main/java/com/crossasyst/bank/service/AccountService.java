package com.crossasyst.bank.service;

import com.crossasyst.bank.entity.AccountEntity;
import com.crossasyst.bank.entity.UserEntity;
import com.crossasyst.bank.mapper.AccountMapper;
import com.crossasyst.bank.model.Account;
import com.crossasyst.bank.repository.AccountRepository;
import com.crossasyst.bank.repository.UserRepository;
import com.crossasyst.bank.response.UserResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class AccountService {

    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private AccountEntity accountEntity = new AccountEntity();

    @Autowired
    public AccountService(AccountMapper accountMapper, AccountRepository accountRepository, UserRepository userRepository) {
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public UserResponse createAccount(Account account, Long userid) {

        log.info("Retrieving user details for used id {} ", userid);

        Optional<UserEntity> optionalUserEntity = Optional.ofNullable(userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("User id not found")));

        optionalUserEntity.ifPresent(userEntity -> {
            accountEntity = accountMapper.modelToEntity(account);
            accountEntity.setUserEntity(userEntity);
            accountRepository.save(accountEntity);
        });

        UserResponse userResponse = new UserResponse();
        userResponse.setId(accountEntity.getAccountNumber());
        log.info("Your account id is {} ", userResponse.getId());

        String accountId = String.valueOf(accountEntity.getAccountNumber());
        return userResponse;
    }

    public Boolean processAccount(Long accountId, Boolean isActive) {
        log.info("Retrieving account details for account id {}", accountId);

        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(accountId);
        AccountEntity accountEntity = accountEntityOptional.orElseThrow(() -> new RuntimeException("Account id not found"));

        accountEntity.setActive(isActive);
        accountRepository.save(accountEntity);

        log.info("Account status updated to {}", isActive);

        return isActive;
    }

}

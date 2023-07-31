package com.crossasyst.bank.service;

import com.crossasyst.bank.entity.AccountEntity;
import com.crossasyst.bank.entity.BeneficaryAccountEntity;
import com.crossasyst.bank.entity.TransactionDetailsEntity;
import com.crossasyst.bank.mapper.TransactionDetailsRepository;
import com.crossasyst.bank.model.TransferRequest;
import com.crossasyst.bank.repository.AccountRepository;
import com.crossasyst.bank.repository.BeneficaryAccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Log4j2
public class TransactionService {

    private final TransactionDetailsRepository transactionDetailsRepository;

    private final AccountRepository accountRepository;

    private final BeneficaryAccountRepository beneficaryAccountRepository;

    TransactionDetailsEntity transactionDetailsEntity = new TransactionDetailsEntity();

    @Autowired
    public TransactionService(TransactionDetailsRepository transactionDetailsRepository, AccountService accountService, AccountRepository accountRepository, BeneficaryAccountRepository beneficaryAccountRepository, BeneficaryAccountService beneficaryAccountService) {
        this.transactionDetailsRepository = transactionDetailsRepository;
        this.accountRepository = accountRepository;
        this.beneficaryAccountRepository = beneficaryAccountRepository;
    }

    public boolean transferFunds(TransferRequest transferRequest) {

        log.info("Retrieving sender, beneficary account number and amount details.");
        Long senderAccountId = transferRequest.getSenderAccountNumber();
        Long beneficiaryAccountId = transferRequest.getBeneficiaryAccountNumber();
        Double amount = transferRequest.getAmount();

        Optional<AccountEntity> senderAccountOptional = Optional.ofNullable(accountRepository.findById(senderAccountId)
                .orElseThrow(() -> new RuntimeException("Sender account number not found " + senderAccountId)));

        Optional<BeneficaryAccountEntity> beneficiaryAccountOptional = Optional.ofNullable(beneficaryAccountRepository.findById(beneficiaryAccountId)
                .orElseThrow(() -> new RuntimeException("Beneficary account number not found " + beneficiaryAccountId)));

        AccountEntity senderAccount = senderAccountOptional.get();
        BeneficaryAccountEntity beneficiaryAccount = beneficiaryAccountOptional.get();
        double senderBalance = calculateSenderBalance(senderAccount, amount);

        if (senderBalance > 0) {
            senderAccount.setCurrentBalance(senderBalance);
            saveTransactionDetails(senderAccount, beneficiaryAccount, amount, true);
            return true;
        } else {
            saveTransactionDetails(senderAccount, beneficiaryAccount, amount, false);
            return false;
        }
    }

    private void saveTransactionDetails(AccountEntity senderAccount, BeneficaryAccountEntity beneficiaryAccount, Double amount, boolean isSuccess) {
        TransactionDetailsEntity transactionDetails = new TransactionDetailsEntity();
        transactionDetails.setSenderAccount(senderAccount);
        transactionDetails.setBeneficiaryAccount(beneficiaryAccount);
        transactionDetails.setAmount(amount);
        transactionDetails.setTimestamp(LocalDateTime.now());
        transactionDetails.setStatus(isSuccess);
        transactionDetailsRepository.save(transactionDetails);
    }

    private double calculateSenderBalance(AccountEntity senderAccount, double amount) {
        double balance = senderAccount.getCurrentBalance();
        if (balance >= amount) {
            return balance - amount;
        } else {
            return -1; // Insufficient balance
        }
    }
}

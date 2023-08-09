package com.crossasyst.camunda.service;

import com.crossasyst.camunda.bank.entity.AccountEntity;
import com.crossasyst.camunda.bank.repository.AccountRepository;
import com.crossasyst.camunda.entity.LoanContractEntity;
import com.crossasyst.camunda.mapper.LoanContractMapper;
import com.crossasyst.camunda.model.LoanContract;
import com.crossasyst.camunda.repository.LoanContractRepository;
import com.lowagie.text.DocumentException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;


@Service
@Log4j2
public class LoanContractService {

    private final TemplateEngine templateEngine;

    private final AccountRepository accountRepository;

    private final LoanContractRepository loanContractRepository;

    private final LoanContractMapper loanContractMapper;

    private LoanContract loanContract = new LoanContract();

    @Autowired
    public LoanContractService(TemplateEngine templateEngine, AccountRepository accountRepository, LoanContractRepository loanContractRepository, LoanContractMapper loanContractMapper) {
        this.templateEngine = templateEngine;
        this.accountRepository = accountRepository;
        this.loanContractRepository = loanContractRepository;
        this.loanContractMapper = loanContractMapper;
    }

    public Long generateLoanContract(Long accountId, Long loanAmount) throws DocumentException {

        log.info("Creating loan contract of account id {} ", accountId);

        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account id not found"));

        String accountHolderFullName = accountEntity.getUserEntity().getLastName() + " " + accountEntity.getUserEntity().getFirstName();

        Context context = new Context();
        context.setVariable("accountHolderFullName", accountHolderFullName);
        context.setVariable("loanAmount", loanAmount);
        context.setVariable("contractDate", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));

        String loanContractHTML = templateEngine.process("loan-contract-template.html", context);

        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(loanContractHTML);
        renderer.layout();
        renderer.createPDF(pdfOutputStream);

        byte[] pdfBytes = pdfOutputStream.toByteArray();

        LoanContractEntity loanContractEntity = new LoanContractEntity();
        loanContractEntity.setAccountEntity(accountEntity);
        loanContractEntity.setLoanContactDraft(pdfBytes);
        loanContractRepository.save(loanContractEntity);
        log.info("Loan contract saved successfully.");
        return loanContractEntity.getLoanContractId();
    }

    public byte[] getLoanContractPDF(Long loanContractId) {

        log.info("Retrieving loan contract of loan contract id {} ", loanContractId);

        LoanContractEntity loanContractEntity = loanContractRepository.findById(loanContractId)
                .orElseThrow(() -> new RuntimeException("Loan contract Id not found"));

        return loanContractEntity.getLoanContactDraft();
    }
}

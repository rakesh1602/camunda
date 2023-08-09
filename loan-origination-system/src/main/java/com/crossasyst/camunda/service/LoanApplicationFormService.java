package com.crossasyst.camunda.service;

import com.crossasyst.camunda.bank.entity.UserEntity;
import com.crossasyst.camunda.bank.repository.UserRepository;
import com.crossasyst.camunda.bank.service.UserService;
import com.crossasyst.camunda.entity.LoanApplicationFormEntity;
import com.crossasyst.camunda.mapper.LoanApplicationFormMapper;
import com.crossasyst.camunda.model.LoanApplicationForm;
import com.crossasyst.camunda.repository.LoanApplicationFormRepository;
import com.crossasyst.camunda.response.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Log4j2
public class LoanApplicationFormService {

    private final LoanApplicationFormRepository loanApplicationFormRepository;

    private final LoanApplicationFormMapper loanApplicationFormMapper;

    private final UserRepository userRepository;

    private LoanApplicationFormEntity loanApplicationFormEntity =new LoanApplicationFormEntity();

    public LoanApplicationFormService(LoanApplicationFormRepository loanApplicationFormRepository, LoanApplicationFormMapper loanApplicationFormMapper, UserRepository userRepository) {
        this.loanApplicationFormRepository = loanApplicationFormRepository;
        this.loanApplicationFormMapper = loanApplicationFormMapper;
        this.userRepository = userRepository;
    }

    public Response createLoanApplicationForm(Long userId,LoanApplicationForm loanApplicationForm) {

        log.info("Retrieving user details");

        Optional<UserEntity> userEntityOptional = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User id not found")));

        if(userEntityOptional.isPresent()){
            loanApplicationFormEntity = loanApplicationFormMapper.modelToEntity(loanApplicationForm);
            loanApplicationFormEntity.setUserEntity(userEntityOptional.get());
            loanApplicationFormRepository.save(loanApplicationFormEntity);
            log.info("Loan application form saved for the user {} ", userId);
        }

        Response response = new Response();
        response.setId(loanApplicationFormEntity.getApplicationFormNo());
        log.info("Loan application number is {} ",response.getId());
        return response;
    }
}

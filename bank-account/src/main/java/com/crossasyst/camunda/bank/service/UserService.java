package com.crossasyst.camunda.bank.service;

import com.crossasyst.camunda.bank.entity.AddressEntity;
import com.crossasyst.camunda.bank.entity.ContactEntity;
import com.crossasyst.camunda.bank.entity.IdentificationEntity;
import com.crossasyst.camunda.bank.entity.UserEntity;
import com.crossasyst.camunda.bank.mapper.UserMapper;
import com.crossasyst.camunda.bank.model.User;
import com.crossasyst.camunda.bank.repository.UserRepository;
import com.crossasyst.camunda.bank.response.UserResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private User user;

    private final AccountService accountService;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository, AccountService accountService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public UserResponse createUser(User user) {
        log.info("Adding user details.");

        UserEntity userEntity = userMapper.modelToEntity(user);
        userEntity.getAddressEntities().forEach(addressEntity -> addressEntity.setUserEntity(userEntity));
        userEntity.getContactEntities().forEach(contactEntity -> contactEntity.setUserEntity(userEntity));
        if(userEntity.getIdentificationEntity()!=null) userEntity.getIdentificationEntity().setUserEntity(userEntity);
        if(userEntity.getAccountEntity()!=null) userEntity.getAccountEntity().setUserEntity(userEntity);

        userRepository.save(userEntity);
        log.info("User details saved successfully.");

        UserResponse userResponse=new UserResponse();
        userResponse.setId(userEntity.getUserId());
        log.info("Your user id is {} ", userResponse.getId());

        accountService.createAccount(user.getAccount(), userEntity.getUserId());

        return userResponse;
    }

    public User getUserById(Long userid) {

        log.info("Retrieving user details for user id {}", userid);

        Optional<UserEntity> userEntityOptional = Optional.of(userRepository.findById(userid).
                orElseThrow(() ->new RuntimeException("User id not found.")));

        userEntityOptional.ifPresent(userEntity -> user = userMapper.entityToModel(userEntity));
        return user;
    }

    public User updateUser(User user, Long userid) {

        log.info("Retrieving user details for user id {}", userid);

        Optional<UserEntity> userEntityOptional = Optional.of(userRepository.findById(userid).
                orElseThrow(() ->new RuntimeException("User id not found.")));

        Long userId = userEntityOptional.get().getUserId();
        List<AddressEntity> oldAddress = userEntityOptional.get().getAddressEntities();
        List<ContactEntity> oldContact = userEntityOptional.get().getContactEntities();
        IdentificationEntity oldIdentification = userEntityOptional.get().getIdentificationEntity();

        userEntityOptional.ifPresent(userEntity -> {
            UserEntity newUserEntity = userMapper.modelToEntity(user);
            newUserEntity.setUserId(userId);
            newUserEntity.setAddressEntities(oldAddress);
            newUserEntity.setContactEntities(oldContact);
            newUserEntity.setIdentificationEntity(oldIdentification);
            userRepository.save(newUserEntity);
        });
        return user;
    }

    public void deleteUser(Long userid) {

        log.info("Retrieving user details for user id {}", userid);

        userRepository.deleteById(userid);
        log.info("Deleted user details for user id {}", userid);
    }
}

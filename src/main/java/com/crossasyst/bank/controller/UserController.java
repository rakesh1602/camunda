package com.crossasyst.bank.controller;

import com.crossasyst.bank.model.User;
import com.crossasyst.bank.response.UserResponse;
import com.crossasyst.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<UserResponse> createUser(@RequestBody @Valid User user){
        UserResponse userResponse = userService.createUser(user);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<User> getUsers(@PathVariable Long userid){
        User user = userService.getUserById(userid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/users/{userid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User>  updateUser(@RequestBody @Valid User user, @PathVariable Long userid){
        user = userService.updateUser(user, userid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{userid}")
    public void deleteUser(@PathVariable Long userid){
        userService.deleteUser(userid);
    }
}

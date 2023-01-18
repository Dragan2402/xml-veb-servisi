package com.users.controller;

import com.users.controller.Requests.LoginRequest;
import com.users.controller.Responses.LoginResponse;
import com.users.model.user.User;
import com.users.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "signUp", produces = {"application/xml"})
    public ResponseEntity<User> signUp(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @PostMapping(value = "login", produces = {"application/xml"})
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }
}

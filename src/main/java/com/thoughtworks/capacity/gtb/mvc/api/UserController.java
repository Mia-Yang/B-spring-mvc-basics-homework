package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user) {
        userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username,
                                      @RequestParam String password) {
        User user = userService.login(username, password);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}

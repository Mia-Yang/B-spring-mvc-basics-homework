package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User("siyu", "1234567" , "siyu@gmail.com"));
    }

    public void register(User newUser) {
        if (users.stream().filter(user -> user.getUsername().equals(newUser.getUsername())).count() > 0) {
            throw new UserExistException("用户已存在");
        }
        users.add(newUser);
    }

}

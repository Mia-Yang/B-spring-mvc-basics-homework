package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserLoginException;
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
        if (findUser(newUser.getUsername()) != null) {
            throw new UserExistException("用户已存在");
        }
        users.add(newUser);
    }

    public User login(String username, String password) {
        if (findUser(username) == null) {
            throw new UserLoginException("用户名或密码错误");
        }
        User findUser = findUser(username);
        if (!findUser.getPassword().equals(password)) {
            throw new UserLoginException("用户名或密码错误");
        }
        return findUser;
    }

    public User findUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().get();
    }
}

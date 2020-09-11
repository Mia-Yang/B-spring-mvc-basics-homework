package com.thoughtworks.capacity.gtb.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int id;

    @Size(min = 3, max = 10, message = "用户名不合法")
    @Pattern(regexp = "^[0-9a-zA-Z_]+$", message = "用户名不合法")
    @NotBlank(message = "用户名不合法")
    String username;

    @Size(min = 5, max = 12, message = "密码不合法")
    String password;

    @Email(message = "邮箱地址不合法")
    String email;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}

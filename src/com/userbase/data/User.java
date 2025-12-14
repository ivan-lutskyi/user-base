/**
 * user-base - A simple user registration and login system
 * 
 * Copyright © 2025 Ivan Lutskyi
 * Email: postscriptum.no@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 */

package com.userbase.data;

/**
 * Represents a user in the system with all their information.
 */
public class User {
    private final String name;
    private final String login;
    private final String sex;
    private final String age;
    private final String password;
    private final String avatarPath;

    public User(String name, String login, String sex, String age, 
                String password, String avatarPath) {
        this.name = name;
        this.login = login;
        this.sex = sex;
        this.age = age;
        this.password = password;
        this.avatarPath = avatarPath != null ? avatarPath : "";
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarPath() {
        return avatarPath;
    }
}


/**
 * user-base - A simple user registration and login system
 * 
 * Copyright © 2025 Ivan Lutskyi
 * Email: postscriptum.no@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 */

package com.userbase.ui;

import com.userbase.data.User;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * Main window displayed after successful login, showing user information and avatar.
 */
public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private static final int HORIZONTAL_PADDING = 20;
    private static final int VERTICAL_PADDING = 20;
    private static final String DEFAULT_AVATAR_PATH = "images/unknown.jpg";

    public MainWindow(User user) {
        super("Main Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeUI(user);
    }

    private void initializeUI(User user) {
        JMenuBar menuBar = new MainWindowMenuBar(this);
        setJMenuBar(menuBar);

        JLabel fullNameLabel = new JLabel("Name: " + user.getName());
        JLabel loginLabel = new JLabel("Login: " + user.getLogin());
        JLabel ageLabel = new JLabel("Age: " + user.getAge());
        JLabel sexLabel = new JLabel("Sex: " + user.getSex());

        BufferedImage avatar = loadAvatar(user.getAvatarPath());
        JLabel avatarLabel = new JLabel(new ImageIcon(avatar));

        Box userBox = Box.createVerticalBox();
        userBox.add(Box.createVerticalStrut(VERTICAL_PADDING));
        userBox.add(avatarLabel);
        userBox.add(fullNameLabel);
        userBox.add(loginLabel);
        userBox.add(ageLabel);
        userBox.add(sexLabel);

        Box workBox = Box.createVerticalBox();
        workBox.add(Box.createVerticalStrut(VERTICAL_PADDING));
        workBox.add(Box.createVerticalStrut(VERTICAL_PADDING));
        workBox.add(Box.createVerticalStrut(VERTICAL_PADDING));

        Box mainBox = Box.createHorizontalBox();
        mainBox.add(Box.createHorizontalStrut(HORIZONTAL_PADDING));
        mainBox.add(userBox);
        mainBox.add(Box.createHorizontalStrut(HORIZONTAL_PADDING));
        mainBox.add(workBox);
        mainBox.add(Box.createHorizontalStrut(HORIZONTAL_PADDING));

        setContentPane(mainBox);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private BufferedImage loadAvatar(String avatarPath) {
        try {
            String path = avatarPath.isEmpty() ? DEFAULT_AVATAR_PATH : avatarPath;
            File avatarFile = new File(path);
            if (avatarFile.exists()) {
                return read(avatarFile);
            } else {
                return read(new File(DEFAULT_AVATAR_PATH));
            }
        } catch (IOException e) {
            try {
                return read(new File(DEFAULT_AVATAR_PATH));
            } catch (IOException ex) {
                System.err.println("Error loading default avatar: " + ex.getMessage());
                return new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            }
        }
    }
}


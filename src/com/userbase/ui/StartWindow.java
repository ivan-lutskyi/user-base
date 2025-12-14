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

import com.userbase.ui.LoginWindow;
import com.userbase.ui.RegisterWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

/**
 * The initial welcome window that allows users to either log in or register.
 */
public class StartWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private static final int PADDING = 12;
    private static final int BUTTON_SPACING = 12;
    private static final int VERTICAL_SPACING = 17;

    public StartWindow() {
        super("Welcome!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        Box box1 = Box.createHorizontalBox();
        JLabel hello = new JLabel("Hello!");
        hello.setAlignmentX(CENTER_ALIGNMENT);
        box1.add(hello);

        Box box2 = Box.createHorizontalBox();
        JLabel action = new JLabel("Please, log in.");
        action.setAlignmentX(CENTER_ALIGNMENT);
        box2.add(action);

        Box box3 = Box.createHorizontalBox();
        JButton signIn = new JButton("Log in");
        JButton register = new JButton("Register");
        
        box3.add(Box.createHorizontalGlue());
        box3.add(signIn);
        box3.add(Box.createHorizontalStrut(BUTTON_SPACING));
        box3.add(register);

        signIn.addActionListener((ActionEvent e) -> {
            dispose();
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });

        register.addActionListener((ActionEvent e) -> {
            dispose();
            RegisterWindow registerWindow = new RegisterWindow();
            registerWindow.setVisible(true);
        });

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(PADDING));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(VERTICAL_SPACING));
        mainBox.add(box3);
        
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }
}


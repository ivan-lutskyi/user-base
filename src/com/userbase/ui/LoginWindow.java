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

import com.userbase.data.UserDataStore;
import com.userbase.data.User;
import com.userbase.ui.StartWindow;
import com.userbase.ui.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

/**
 * Login window that allows existing users to authenticate and access their account.
 */
public class LoginWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private static final int PADDING = 12;
    private static final int FIELD_SPACING = 6;
    private static final int BUTTON_SPACING = 12;
    private static final int VERTICAL_SPACING = 17;
    
    private final UserDataStore dataStore;

    public LoginWindow() {
        super("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.dataStore = new UserDataStore();
        initializeUI();
    }

    private void initializeUI() {
        Box box1 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(15);

        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(FIELD_SPACING));
        box1.add(loginField);

        Box box2 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(FIELD_SPACING));
        box2.add(passwordField);

        Box box3 = Box.createHorizontalBox();
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener((ActionEvent e) -> handleLogin(loginField, passwordField));

        cancel.addActionListener((ActionEvent e) -> {
            dispose();
            StartWindow startWindow = new StartWindow();
            startWindow.setVisible(true);
        });

        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(BUTTON_SPACING));
        box3.add(cancel);

        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());

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

    private void handleLogin(JTextField loginField, JPasswordField passwordField) {
        String login = loginField.getText().trim();
        char[] password = passwordField.getPassword();
        String passwordStr = new String(password);

        if (login.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this, "Please enter login and password", 
                                         "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            User user = dataStore.findUser(login, passwordStr);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "User found", 
                                             "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                
                MainWindow mainWindow = new MainWindow(user);
                mainWindow.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "User not found or incorrect password", 
                                             "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error reading user data: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


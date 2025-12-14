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
import com.userbase.ui.StartWindow;
import com.userbase.ui.LoginWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Registration window that allows new users to create an account.
 * Collects user information including name, login, password, age, sex, and avatar.
 */
public class RegisterWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private static final int PADDING = 12;
    private static final int FIELD_SPACING = 6;
    private static final int BUTTON_SPACING = 12;
    private static final String DEFAULT_AVATAR_PATH = "images/unknown.jpg";
    
    private String avatarFilePath = "";
    private final UserDataStore dataStore;

    public RegisterWindow() {
        super("Register");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.dataStore = new UserDataStore();
        initializeUI();
    }

    private void initializeUI() {
        Box avatarBox = createAvatarBox();
        Box nameBox = createTextFieldBox("Full name:", 15);
        Box loginBox = createTextFieldBox("Login:", 15);
        Box sexBox = createSexSelectionBox();
        Box ageBox = createTextFieldBox("Age:", 3);
        Box passwordBox = createPasswordFieldBox("Password:");
        Box passwordConfirmBox = createPasswordFieldBox("Confirm password:");
        
        JTextField nameField = (JTextField) nameBox.getComponent(2);
        JTextField loginField = (JTextField) loginBox.getComponent(2);
        JTextField ageField = (JTextField) ageBox.getComponent(2);
        JPasswordField passwordField = (JPasswordField) passwordBox.getComponent(2);
        JPasswordField passwordConfirmField = (JPasswordField) passwordConfirmBox.getComponent(2);
        JCheckBox maleCheckBox = (JCheckBox) sexBox.getComponent(2);
        JCheckBox femaleCheckBox = (JCheckBox) sexBox.getComponent(4);
        
        Box buttonBox = createButtonBox(nameField, loginField, ageField, passwordField, 
                                       passwordConfirmField, maleCheckBox, femaleCheckBox);

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        mainBox.add(nameBox);
        mainBox.add(Box.createVerticalStrut(PADDING));
        mainBox.add(ageBox);
        mainBox.add(Box.createVerticalStrut(PADDING));
        mainBox.add(loginBox);
        mainBox.add(Box.createVerticalStrut(PADDING));
        mainBox.add(passwordBox);
        mainBox.add(Box.createVerticalStrut(PADDING));
        mainBox.add(passwordConfirmBox);
        mainBox.add(Box.createVerticalStrut(PADDING));
        mainBox.add(sexBox);
        mainBox.add(Box.createVerticalStrut(PADDING));
        mainBox.add(avatarBox);
        mainBox.add(Box.createVerticalStrut(PADDING));
        mainBox.add(buttonBox);

        setContentPane(mainBox);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private Box createAvatarBox() {
        Box avatarBox = Box.createHorizontalBox();
        JLabel avatarLabel = new JLabel("Avatar:");
        JButton chooseButton = new JButton("Choose file");

        chooseButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showDialog(null, "Open file");
            if (result == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fileChooser.getSelectedFile();
                avatarFilePath = file.getPath();
            }
        });
        
        avatarBox.add(avatarLabel);
        avatarBox.add(Box.createHorizontalStrut(FIELD_SPACING));
        avatarBox.add(chooseButton);
        return avatarBox;
    }

    private Box createTextFieldBox(String labelText, int columns) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(labelText);
        JTextField field = new JTextField(columns);
        box.add(label);
        box.add(Box.createHorizontalStrut(FIELD_SPACING));
        box.add(field);
        return box;
    }

    private Box createPasswordFieldBox(String labelText) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(labelText);
        JPasswordField field = new JPasswordField(15);
        box.add(label);
        box.add(Box.createHorizontalStrut(FIELD_SPACING));
        box.add(field);
        return box;
    }

    private Box createSexSelectionBox() {
        Box box = Box.createHorizontalBox();
        JLabel sexLabel = new JLabel("Choose sex:");
        JCheckBox male = new JCheckBox("male");
        JCheckBox female = new JCheckBox("female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        box.add(sexLabel);
        box.add(Box.createHorizontalStrut(FIELD_SPACING));
        box.add(male);
        box.add(Box.createHorizontalStrut(FIELD_SPACING));
        box.add(female);
        return box;
    }

    private Box createButtonBox(JTextField nameField, JTextField loginField, 
                               JTextField ageField, JPasswordField passwordField,
                               JPasswordField passwordConfirmField, JCheckBox maleCheckBox,
                               JCheckBox femaleCheckBox) {
        Box box = Box.createHorizontalBox();
        JButton register = new JButton("Register");
        JButton cancel = new JButton("Cancel");

        register.addActionListener((ActionEvent e) -> handleRegistration(
            nameField, loginField, ageField, passwordField, 
            passwordConfirmField, maleCheckBox, femaleCheckBox));

        cancel.addActionListener((ActionEvent e) -> {
            dispose();
            StartWindow startWindow = new StartWindow();
            startWindow.setVisible(true);
        });

        box.add(Box.createHorizontalGlue());
        box.add(register);
        box.add(Box.createHorizontalStrut(BUTTON_SPACING));
        box.add(cancel);
        return box;
    }

    private void handleRegistration(JTextField nameField, JTextField loginField,
                                   JTextField ageField, JPasswordField passwordField,
                                   JPasswordField passwordConfirmField, JCheckBox maleCheckBox,
                                   JCheckBox femaleCheckBox) {
        String name = nameField.getText().trim();
        String login = loginField.getText().trim();
        String age = ageField.getText().trim();
        char[] password = passwordField.getPassword();
        char[] passwordConfirm = passwordConfirmField.getPassword();

        String sex = "";
        if (maleCheckBox.isSelected()) {
            sex = "Male";
        } else if (femaleCheckBox.isSelected()) {
            sex = "Female";
        }

        if (!validateInput(name, login, age, password, passwordConfirm, sex)) {
            return;
        }

        if (!arePasswordsEqual(password, passwordConfirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String passwordStr = new String(password);
        String finalAvatarPath = avatarFilePath.isEmpty() ? DEFAULT_AVATAR_PATH : avatarFilePath;

        try {
            dataStore.saveUser(name, login, sex, age, passwordStr, finalAvatarPath);
            JOptionPane.showMessageDialog(this, "Registration successful!", 
                                         "Success", JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error saving user data: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateInput(String name, String login, String age,
                                 char[] password, char[] passwordConfirm, String sex) {
        if (name.isEmpty() || login.isEmpty() || age.isEmpty() || 
            password.length == 0 || passwordConfirm.length == 0 || sex.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", 
                                         "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int ageValue = Integer.parseInt(age);
            if (ageValue < 0 || ageValue > 150) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age", 
                                             "Validation Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number", 
                                         "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private static boolean arePasswordsEqual(char[] pass1, char[] pass2) {
        return Arrays.equals(pass1, pass2);
    }
}


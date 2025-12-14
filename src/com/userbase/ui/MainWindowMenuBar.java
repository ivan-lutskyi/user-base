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

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Menu bar for the main window, providing options to exit or change user.
 */
public class MainWindowMenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    public MainWindowMenuBar(MainWindow mainWindow) {
        super();
        initializeMenu(mainWindow);
    }

    private void initializeMenu(MainWindow mainWindow) {
        JMenu fileMenu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");

        fileMenu.add(exit);

        JMenu userMenu = new JMenu("User");
        JMenuItem logIn = new JMenuItem("Change user/Log in");
        JMenuItem register = new JMenuItem("Register");

        userMenu.add(logIn);
        userMenu.add(register);

        add(fileMenu);
        add(userMenu);

        exit.addActionListener((ActionEvent actionEvent) -> {
            int option = JOptionPane.showConfirmDialog(exit, "Are you sure?", 
                                                      "Exit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        logIn.addActionListener((ActionEvent actionEvent) -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
            mainWindow.dispose();
        });

        register.addActionListener((ActionEvent actionEvent) -> {
            RegisterWindow registerWindow = new RegisterWindow();
            registerWindow.setVisible(true);
            mainWindow.dispose();
        });
    }
}


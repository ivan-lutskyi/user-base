/**
 * user-base - A simple user registration and login system
 * 
 * Copyright © 2025 Ivan Lutskyi
 * Email: postscriptum.no@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 */

package com.userbase;

import com.userbase.ui.StartWindow;

/**
 * Main entry point for the user-base application.
 * Launches the start window for user registration and login.
 */
public class UserBase {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            StartWindow startWindow = new StartWindow();
            startWindow.setVisible(true);
        });
    }
}


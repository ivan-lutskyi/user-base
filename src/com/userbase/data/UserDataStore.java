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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles persistence of user data to and from a text file.
 * Uses a simple text-based format for storage.
 */
public class UserDataStore {
    private static final String DATA_FILE = "base.txt";
    private static final String FIELD_SEPARATOR = "&end";

    /**
     * Saves a new user to the data store.
     * 
     * @param name User's full name
     * @param login User's login
     * @param sex User's sex
     * @param age User's age
     * @param password User's password
     * @param avatarPath Path to user's avatar image
     * @throws IOException if writing to file fails
     */
    public void saveUser(String name, String login, String sex, 
                        String age, String password, String avatarPath) throws IOException {
        String dataToBase = String.format(
            "Name: [%s] Login: [%s] Sex: [%s] Age: [%s] Password is: [%s] AvatarPath: [%s]%s%n",
            name, login, sex, age, password, avatarPath, FIELD_SEPARATOR
        );

        try (FileWriter writer = new FileWriter(DATA_FILE, true)) {
            writer.write(dataToBase);
            writer.flush();
        }
    }

    /**
     * Finds a user by login and password.
     * 
     * @param login User's login
     * @param password User's password
     * @return User object if found, null otherwise
     * @throws IOException if reading from file fails
     */
    public User findUser(String login, String password) throws IOException {
        if (!Files.exists(Paths.get(DATA_FILE))) {
            return null;
        }

        try (Scanner scanner = new Scanner(new File(DATA_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Login: [" + login + "]") && 
                    line.contains(" Password is: [" + password + "]")) {
                    return parseUserFromLine(line, login, password);
                }
            }
        }
        return null;
    }

    private User parseUserFromLine(String line, String login, String password) {
        try {
            String name = extractField(line, "Name: [", "] Login");
            String sex = extractField(line, "Sex: [", "] Age");
            String age = extractField(line, " Age: [", "] Password");
            String avatarPath = extractField(line, "AvatarPath: [", "]&end");

            return new User(name, login, sex, age, password, avatarPath);
        } catch (Exception e) {
            System.err.println("Error parsing user data: " + e.getMessage());
            return null;
        }
    }

    private String extractField(String line, String startMarker, String endMarker) {
        int startIndex = line.indexOf(startMarker);
        if (startIndex == -1) {
            return "";
        }
        startIndex += startMarker.length();
        
        int endIndex = line.indexOf(endMarker, startIndex);
        if (endIndex == -1) {
            return "";
        }
        
        return line.substring(startIndex, endIndex);
    }
}


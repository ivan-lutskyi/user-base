# user-base

A simple Java Swing application for user registration and login with a file-based storage system.

> **Note:** This is an older archived experiment that has been cleaned up and modernized for presentation on GitHub.

## Overview

user-base is a desktop application that provides a user management system with the following features:

- User registration with profile information (name, login, password, age, sex, avatar)
- User login and authentication
- Profile display after successful login
- File-based data persistence

The application was originally created as a learning project and includes UML diagrams for various aspects of the system design.

## Tech Stack

- **Language:** Java
- **GUI Framework:** Java Swing
- **Storage:** Text file-based (`base.txt`)
- **Build Tool:** Makefile

## Screenshots

### Start Window
![Start Window](Screenshots/start_win.png)

### Registration
![Registration Window](Screenshots/register_win_1.png)
![Registration Form](Screenshots/register_win_2.png)

### Login
![Login Window](Screenshots/login_win_1.png)
![Login Form](Screenshots/login_win_2.png)

### Main Window
![Main Window](Screenshots/main_win.png)

## Installation & Running

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Make (optional, for using the Makefile)

### Build and Run

#### Using Makefile:

```bash
make run
```

#### Manual compilation:

```bash
# Compile
javac -d build/classes -sourcepath src src/com/userbase/UserBase.java

# Run
java -cp build/classes com.userbase.UserBase
```

#### Direct run (if already compiled):

```bash
java -cp build/classes com.userbase.UserBase
```

## Project Structure

```
user-base/
├── src/com/userbase/
│   ├── UserBase.java              # Main entry point
│   ├── ui/                        # User interface components
│   │   ├── StartWindow.java
│   │   ├── RegisterWindow.java
│   │   ├── LoginWindow.java
│   │   ├── MainWindow.java
│   │   └── MainWindowMenuBar.java
│   └── data/                      # Data layer
│       ├── User.java
│       └── UserDataStore.java
├── images/                        # Avatar images
├── Screenshots/                   # Application screenshots
├── Diagrams/                      # UML diagrams
├── base.txt                       # User data storage file (created at runtime)
├── Makefile                       # Build configuration
└── README.md                      # This file
```

## Features

- **User Registration:** Create new accounts with full profile information
- **User Login:** Authenticate existing users
- **Profile Display:** View user information after login
- **Avatar Support:** Optional avatar image selection
- **Data Persistence:** User data stored in `base.txt` file

## UML Diagrams

The project includes UML diagrams located in the `Diagrams/` directory:

- Use-case diagrams
- Sequence diagrams
- Collaboration diagrams
- Class diagrams
- Statechart diagrams

## Data Storage

User data is stored in a simple text file format (`base.txt`) with the following structure:

```
Name: [Name] Login: [Login] Sex: [Sex] Age: [Age] Password is: [Password] AvatarPath: [Path]&end
```

## Limitations

- Simple text-based storage (not suitable for production)
- No password encryption
- No duplicate login checking
- Basic error handling

## Author

**Ivan Lutskyi**  
Email: postscriptum.no@gmail.com

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Copyright © 2025 Ivan Lutskyi

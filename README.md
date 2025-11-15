# MAD_Codes
Mobile Application Development Course Lab Programs (XML + JAVA)

## Project Structure

This repository contains a complete Android development environment setup with XML layouts and Java code.

### Directory Structure
```
MAD_Codes/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── example/
│   │       │           └── madcodes/
│   │       │               └── MainActivity.java
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   └── activity_main.xml
│   │       │   ├── values/
│   │       │   │   ├── strings.xml
│   │       │   │   └── colors.xml
│   │       │   ├── drawable/
│   │       │   └── mipmap-*/
│   │       └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
├── gradle.properties
├── .gitignore
└── README.md
```

## Features

- **Java Source Code**: MainActivity.java demonstrates basic Android Activity structure
- **XML Layouts**: activity_main.xml showcases RelativeLayout with TextView and Button
- **Resource Files**: Proper organization of strings, colors, and other resources
- **Gradle Build System**: Complete build configuration for Android development
- **AndroidManifest.xml**: Application configuration with proper permissions and activities

## Getting Started

### Prerequisites
- Android Studio (Arctic Fox or later recommended)
- JDK 8 or higher
- Android SDK (API Level 21 or higher)

### Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/Venkatesh123-start/MAD_Codes.git
```

2. Open the project in Android Studio:
   - Launch Android Studio
   - Click "Open an existing project"
   - Navigate to the cloned repository folder
   - Select the project and click "OK"

3. Sync Gradle:
   - Android Studio will automatically prompt to sync Gradle
   - Click "Sync Now" if prompted

4. Run the application:
   - Connect an Android device or start an emulator
   - Click the "Run" button (green play icon) in Android Studio
   - Select your device/emulator and click "OK"

## Project Configuration

- **Package Name**: com.example.madcodes
- **Minimum SDK**: API 21 (Android 5.0 Lollipop)
- **Target SDK**: API 33 (Android 13)
- **Compile SDK**: API 33

## Dependencies

- AndroidX AppCompat: 1.6.1
- Material Components: 1.9.0
- ConstraintLayout: 2.1.4
- JUnit: 4.13.2 (for testing)

## Contributing

This is a course lab program repository. Feel free to add more lab programs and examples.

## License

This project is for educational purposes.

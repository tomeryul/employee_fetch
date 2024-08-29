# Employee Management App

An Android application that retrieves employee information from [Reqres](https://reqres.in/) using Retrofit, saves the data locally using Gson, and allows you to manage (add, edit, delete) employee records. The list of employees is displayed using a ListView with images on CardView.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Screenshots](#screenshots)
- [Usage](#usage)

## Introduction

This app is designed to manage employee data efficiently. It fetches data from an online API, stores it locally, and provides a user-friendly interface to view and manage the information. The app uses Retrofit for network operations, Gson for data serialization, and displays employee information using a combination of ListView and CardView.

## Features

- Retrieve employee data from [Reqres](https://reqres.in/)
- Save data locally using Gson
- Add, edit, and delete employee records
- Display employee data with images in a ListView using CardView

## Screenshots

![Screenshot 1](path/to/screenshot1.png)
![Screenshot 2](path/to/screenshot2.png)
<!-- Add more screenshots as needed -->


### Prerequisites

- Android Studio 4.0 or later
- Android SDK
- An Android device or emulator running Android 5.0 (Lollipop) or later

### Steps

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/employee-management-app.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device.

## Usage

- **Fetching Employees**: The app automatically retrieves employee data from the API when launched.
- **Managing Employees**: You can add new employees, edit existing ones, or delete them. The data is stored locally, ensuring your changes are saved.
- **Viewing Employees**: Employees are displayed in a ListView, with each item represented by a CardView that includes the employee's image and details.




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

# Loading and Fetching:

![loadingAndFetching](https://github.com/user-attachments/assets/eecd37bd-e44a-4b4b-a233-cf7b8734fa8a)

# Editing an employee:

![EditingEmployee](https://github.com/user-attachments/assets/1ee94a76-0ebd-4faa-9e36-a653c2a1cd1a)

# Deliting an employee:

![DeleteEmployee](https://github.com/user-attachments/assets/ddd69423-b71d-4c83-986c-470987da42a4)

# Adding new employee:

![AddEmployee](https://github.com/user-attachments/assets/791abe67-2475-4a35-8484-7f5c65981c50)

# Loading the app again to see the local data information: 

![CheckIfSaved](https://github.com/user-attachments/assets/6cfb8e3b-e1a4-4f9c-95d6-17a55ef469e9)


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




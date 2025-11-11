# Course Title: Object-Oriented Programming | Course Code: CSE213/ CSC305 
## Group: Team 9 (Autumn 2023)
## Group Name : Grove Street
# KFC Global Operations Simulation

![KFC Logo](kfc_logo.png)

Welcome to the KFC Global Operations Simulation project! This simulation aims to provide a comprehensive overview of the global operations of KFC, the renowned fast-food chain famous for its delicious fried chicken.

## Overview

This project utilizes object-oriented programming principles to simulate various aspects of KFC's business, including supply chain management, restaurant operations, customer interactions, and financial transactions.

## Features

- **Supply Chain Management:** Procurement and inventory management of raw materials.
  
- **Restaurant Operations:** Simulation of kitchen activities, order processing, and employee management.
  
- **Customer Interactions:** Simulation of customer visits, order placement, payment transactions, and feedback collection.
  
- **Financial Transactions:** Modeling of sales, expenses, and profitability for each restaurant.
  
- **Global Expansion:** Simulation of new restaurant openings in different locations.

## Installation

Run locally on Windows using NetBeans (recommended) or CLI.

### NetBeans (Ant + JavaFX)
- Install JDK 20+ (JDK 21 recommended) and JavaFX SDK 21.
- In NetBeans, set Project Properties → Libraries → Add the JavaFX SDK `lib/*.jar`.
- Ensure Project Properties → JavaFX has `Main Class` set to `mainpkg.CustomerApplication`.
- Run the project; the Login screen should open.

### CLI (javac/java)
1. Download JavaFX SDK 21 and set `JAVA_FX_LIB` to the `lib` folder path:

    ```powershell
    $env:JAVA_FX_LIB = "C:\dev\javafx-sdk-21\lib"
    ```

2. Compile sources (from project root):

    ```powershell
    javac --module-path "$env:JAVA_FX_LIB" --add-modules javafx.controls,javafx.fxml -d build/classes $(Get-ChildItem -Recurse src/*.java | % { $_.FullName })
    ```

3. Run the application:

    ```powershell
    java --module-path "$env:JAVA_FX_LIB" --add-modules javafx.controls,javafx.fxml -cp build/classes mainpkg.CustomerApplication
    ```

### Ant (if installed)
- From the project root, try:

    ```powershell
    ant run
    ```

  Ensure the JavaFX SDK is available on the Ant classpath; NetBeans-managed Ant builds use `nbproject/jfx-impl.xml` and `javafx.*` properties (see `nbproject/project.properties`).

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please open an issue to discuss your ideas or submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

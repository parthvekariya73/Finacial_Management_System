# Finacial_Management_System
Welcome to the Financial Management System repository! This repository contains a comprehensive Java package for managing various financial operations. The package includes classes for handling user connections, data retrieval and storage, user authentication, and dashboard management. This system uses JDBC to connect to a MySQL database for dynamic data operations.

# Table of Contents
Introduction
Folder Structure
Getting Started
Class Overview
Database Configuration
Contributing
License

# Introduction
The Financial Management System is designed to manage financial data efficiently. It supports user registration and login, data manipulation for various financial entities (such as expenses, income, investments, loans, and real estate), and provides a dashboard for users to view their financial status.

# Folder Structure
The repository is organized as follows:

Financial_Management_System/<br>
├── Connections/<br>
│   ├── ConnectionProvider.class<br>
│   ├── CreateUserConnection.class<br>
│   ├── Connections.class<br>
├── DeshBoard.class<br>
├── GetDataTable/<br>
│   ├── ExpClass.class<br>
│   ├── IStockClass.class<br>
│   ├── LoanCLass.class<br>
│   ├── Portfolio/<br>
│   │   ├── Assets.class<br>
│   │   ├── Expenses.class<br>
│   │   ├── Income.class<br>
│   │   ├── Liabilities.class<br>
│   │   ├── Portfolio.class<br>
│   ├── RealEstateClass.class<br>
│   ├── TStockClass.class<br>
│   ├── TableData.class<br>
│   ├── GetDataTable.class<br>
├── Main.class<br>
├── SetDataTable/<br>
│   ├── tbl_exp.class<br>
│   ├── tbl_investmentstock.class<br>
│   ├── tbl_loan.class<br>
│   ├── tbl_realestate.class<br>
│   ├── tbl_tradingshare.class<br>
│   ├── SetDataTable.class<br>
├── User/<br>
│   ├── Registration.class<br>
│   ├── login.class<br>
│   ├── User.class<br>
└── README.md<br>

# Getting Started
To get started with the Financial Management System, follow these steps:

Clone the repository:
git clone https://github.com/yourusername/Financial_Management_System.git
cd Financial_Management_System

Set up the MySQL database
Create a new MySQL database.
Import the provided SQL script ("fm_db.sql") to set up the necessary tables and data.
Configure the database connection<br>
Update the ConnectionProvider class with your database credentials.<br>
Compile and run the application:<br>
javac -d bin src/*.java<br>
java -cp bin Main<br>

# Class Overview
Connections<br>
ConnectionProvider: Provides a connection to the MySQL database.<br>
CreateUserConnection: Manages user-specific connections.<br>
Connections: Manages general database connections.<br>
Dashboard<br>
DeshBoard: Handles the user interface for displaying financial data.<br>
Data Retrieval<br>
GetDataTable: Main class for retrieving data from the database.<br>
ExpClass: Retrieves expense data.<br>
IStockClass: Retrieves investment stock data.<br>
LoanClass: Retrieves loan data.<br>
Portfolio: Retrieves portfolio data.<br>
Assets: Retrieves asset data.<br>
Expenses: Retrieves expense data within the portfolio.<br>
Income: Retrieves income data.<br>
Liabilities: Retrieves liabilities data.<br>
RealEstateClass: Retrieves real estate data.<br>
TStockClass: Retrieves trading stock data.<br>
TableData: Retrieves general table data.<br>
Data Manipulation<br>
SetDataTable: Main class for inserting and updating data in the database.<br>
tbl_exp: Handles expense data.<br>
tbl_investmentstock: Handles investment stock data.<br>
tbl_loan: Handles loan data.<br>
tbl_realestate: Handles real estate data.<br>
tbl_tradingshare: Handles trading share data.<br>
User Management<br>
User: Main class for user operations.<br>
Registration: Manages user registration.<br>
login: Manages user login.<br>

# Contributing
We welcome contributions to this project! If you have any improvements or new features to add, please follow these steps:<br>

Fork the repository.<br>
Create a new branch (git checkout -b feature-branch).<br>
Commit your changes (git commit -m 'Add new feature').<br>
Push to the branch (git push origin feature-branch).<br>
Create a new Pull Request.<br>
<br>
License : 
This project is licensed under the MIT License. See the LICENSE file for more details.<br>

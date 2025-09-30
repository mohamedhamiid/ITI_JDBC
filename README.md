# ITI JDBC Contact Management System

A comprehensive Java application demonstrating JDBC connectivity with MySQL database using two different connection approaches: **DataSource** and **DriverManager**. This project showcases contact management functionality with CRUD operations, batch processing, and proper database connection handling.

## 🎯 Project Overview

This educational project demonstrates:
- Two different JDBC connection methodologies
- Contact management system with comprehensive CRUD operations
- Batch processing for improved performance
- Proper resource management and connection handling
- Properties-based configuration

## 📁 Project Structure

```
ITI_JDBC/
├── ITI_JDBC.iml
├── README.md                           # This file
└── src/
    ├── mysql-connector-j-9.4.0.jar   # MySQL JDBC Driver
    ├── ContactPersonWithDataSource/   # DataSource implementation
    │   ├── ContactPerson.java
    │   ├── ContactPersonDAO.java
    │   ├── Main.java
    │   ├── file.properties
    │   └── README.md
    └── ContactPersonWithDriverManager/ # DriverManager implementation
        ├── ContactPerson.java
        ├── ContactPersonDAO.java
        └── Main.java
```

## 🚀 Features

### Core Functionality
- ✅ **Contact Management**: Create, read, update contact information
- ✅ **Database Table Creation**: Automatic table creation with proper schema
- ✅ **Search Operations**: Find contacts by name with pattern matching
- ✅ **Batch Processing**: Efficient batch email updates
- ✅ **Connection Management**: Proper resource cleanup and connection handling

### Contact Information Fields
- Personal Details: Name, Nickname, Address
- Communication: Home Phone, Work Phone, Cellphone, Email
- Professional: Website, Profession
- Additional: Birthdate

### Two Implementation Approaches

#### 1. DataSource Implementation (`ContactPersonWithDataSource`)
- Uses `MysqlDataSource` for connection pooling
- Properties-based configuration
- Better for production environments
- Enhanced connection management

#### 2. DriverManager Implementation (`ContactPersonWithDriverManager`)
- Direct `DriverManager` usage
- Hardcoded connection parameters
- Simple and straightforward approach
- Good for learning and small applications

## 🛠️ Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **MySQL Database**: Version 5.7 or higher
- **MySQL Connector/J**: Version 9.4.0 (included in project)
- **IDE**: IntelliJ IDEA, Eclipse, or any Java IDE

## 📋 Database Setup

1. **Install MySQL Server** (if not already installed)
2. **Create Database**:
   ```sql
   CREATE DATABASE mydb;
   USE mydb;
   ```

3. **Configure Database User** (if needed):
   ```sql
   CREATE USER 'root'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON mydb.* TO 'root'@'localhost';
   FLUSH PRIVILEGES;
   ```

## ⚙️ Configuration

### For DataSource Implementation

Edit `src/ContactPersonWithDataSource/file.properties`:

```properties
MYSQL_DB_URL = jdbc:mysql://localhost:3306/mydb
DRIVER = com.mysql.cj.jdbc.Driver
USER = root
PASSWORD = your_password_here
```

### For DriverManager Implementation

Update connection parameters in `ContactPersonWithDriverManager/Main.java`:

```java
String driverPath = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/mydb";
String username = "root";
String password = "your_password_here";
```

## 🏃‍♂️ How to Run

### Method 1: Using IDE
1. Import the project into your IDE
2. Ensure MySQL server is running
3. Configure database connection parameters
4. Run either:
   - `ContactPersonWithDataSource.Main`
   - `ContactPersonWithDriverManager.Main`

### Method 2: Command Line
```bash
# Navigate to project directory
cd ITI_JDBC/src

# Compile (DataSource version)
javac -cp "mysql-connector-j-9.4.0.jar" ContactPersonWithDataSource/*.java

# Run (DataSource version)
java -cp ".:mysql-connector-j-9.4.0.jar" ContactPersonWithDataSource.Main

# Compile (DriverManager version)
javac -cp "mysql-connector-j-9.4.0.jar" ContactPersonWithDriverManager/*.java

# Run (DriverManager version)
java -cp ".:mysql-connector-j-9.4.0.jar" ContactPersonWithDriverManager.Main
```

### For Windows PowerShell:
```powershell
# Compile (DataSource version)
javac -cp "mysql-connector-j-9.4.0.jar" ContactPersonWithDataSource/*.java

# Run (DataSource version)
java -cp ".;mysql-connector-j-9.4.0.jar" ContactPersonWithDataSource.Main
```

## 📊 Application Flow

Both implementations follow the same testing flow:

1. **Initialize DAO** - Set up database connection
2. **Create Table** - Create contacts table if it doesn't exist
3. **Insert Contacts** - Add sample contact records
4. **Retrieve All** - Display all contacts in database
5. **Search Contacts** - Find contacts by name pattern
6. **Batch Update** - Update all email addresses in batch
7. **Cleanup** - Close database connections

## 🗃️ Database Schema

The application creates a `contacts` table with the following structure:

```sql
CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    address TEXT,
    home_phone VARCHAR(20),
    work_phone VARCHAR(20),
    cellphone VARCHAR(20),
    email VARCHAR(100),
    birthdate DATE,
    website VARCHAR(100),
    profession VARCHAR(100)
);
```

## 🔍 Key Learning Points

### DataSource vs DriverManager
- **DataSource**: Better for production, supports connection pooling, externalized configuration
- **DriverManager**: Simpler for learning, direct connection management, hardcoded parameters

### Best Practices Demonstrated
- ✅ Proper resource management with try-finally blocks
- ✅ Prepared statements for SQL injection prevention
- ✅ Batch processing for improved performance
- ✅ Separation of concerns (DAO pattern)
- ✅ Configuration externalization

## 🚨 Troubleshooting

### Common Issues

1. **Connection Refused**
   - Ensure MySQL server is running
   - Check connection parameters (host, port, database name)

2. **Authentication Failed**
   - Verify username and password
   - Check user privileges for the database

3. **ClassNotFoundException**
   - Ensure MySQL Connector/J is in classpath
   - Verify JAR file is not corrupted

4. **Table Already Exists**
   - The application handles this gracefully
   - Drop the table manually if needed: `DROP TABLE contacts;`

## 📚 Additional Resources

- [MySQL Connector/J Documentation](https://dev.mysql.com/doc/connector-j/8.0/en/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
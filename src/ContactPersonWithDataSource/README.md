# ContactPerson JDBC Application

A Java application demonstrating JDBC connectivity with MySQL database using DataSource for managing contact information.

## Project Structure

```
ContactPersonWithDataSource/
├── ContactPerson.java          # Contact entity class
├── ContactPersonDAO.java       # Data Access Object for database operations
├── Main.java                   # Main application entry point
├── file.properties            # Database configuration
└── README.md                  # This file
```

## Features

- **Database Connection Management**: Uses MySQL DataSource for connection pooling
- **CRUD Operations**: Create, Read, Update operations for contact records
- **Batch Processing**: Batch email updates for improved performance
- **Search Functionality**: Search contacts by name with wildcard matching
- **Transaction Management**: Proper transaction handling for batch operations

## Prerequisites

- Java 8 or higher
- MySQL Server 5.7+ or 8.0+
- MySQL Connector/J 8.0+ JAR file

## Database Setup

1. **Install MySQL Server** if not already installed
2. **Create Database**:
   ```sql
   CREATE DATABASE mydb;
   USE mydb;
   ```

3. **Configure Database Connection**:
   Update `file.properties` with your database credentials:
   ```properties
   MYSQL_DB_URL = jdbc:mysql://localhost:3306/mydb
   DRIVER = com.mysql.cj.jdbc.Driver
   USER = your_username
   PASSWORD = your_password
   ```

## Installation & Setup

1. **Download MySQL Connector/J**:
   - Download from: https://dev.mysql.com/downloads/connector/j/
   - Place the JAR file (e.g., `mysql-connector-j-8.2.0.jar`) in your project directory

2. **Compile the Project**:
   ```bash
   javac -cp "mysql-connector-j-8.2.0.jar" *.java
   ```

3. **Run the Application**:
   ```bash
   java -cp ".;mysql-connector-j-8.2.0.jar" ContactPersonWithDataSource.Main
   ```

## Class Overview

### ContactPerson.java
Entity class representing a contact with the following attributes:
- `id` - Unique identifier (auto-generated)
- `name` - Full name
- `nickName` - Nickname
- `address` - Home address
- `homePhone` - Home phone number
- `workPhone` - Work phone number
- `cellphone` - Mobile phone number
- `mail` - Email address
- `birthdate` - Date of birth
- `website` - Personal website
- `profession` - Job title/profession

### ContactPersonDAO.java
Data Access Object providing database operations:
- `createContactTable()` - Creates the ContactPerson table if it doesn't exist
- `insertContactPerson(ContactPerson person)` - Inserts a new contact
- `getContacts()` - Retrieves all contacts
- `getContactsForName(String name)` - Searches contacts by name
- `updateEmails()` - Batch updates all contact emails
- `closeConnection()` - Closes database connection

### Main.java
Demonstration application that:
1. Initializes the DAO
2. Creates the database table
3. Inserts sample contacts
4. Retrieves and displays all contacts
5. Performs name-based search
6. Properly closes connections

## Database Schema

The application creates a `ContactPerson` table with the following structure:

```sql
CREATE TABLE ContactPerson (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    address VARCHAR(200),
    homePhone VARCHAR(20),
    workPhone VARCHAR(20),
    cellphone VARCHAR(20),
    mail VARCHAR(100),
    birthdate DATE,
    website VARCHAR(100),
    profession VARCHAR(100)
);
```

## Usage Examples

### Creating a New Contact
```java
ContactPerson contact = new ContactPerson();
contact.setName("John Doe");
contact.setMail("john.doe@email.com");
contact.setProfession("Software Engineer");

ContactPersonDAO dao = new ContactPersonDAO();
boolean success = dao.insertContactPerson(contact);
```

### Retrieving All Contacts
```java
ContactPersonDAO dao = new ContactPersonDAO();
List<ContactPerson> contacts = dao.getContacts();
for (ContactPerson contact : contacts) {
    System.out.println(contact.getName() + " - " + contact.getMail());
}
```

### Searching Contacts
```java
ContactPersonDAO dao = new ContactPersonDAO();
List<ContactPerson> results = dao.getContactsForName("John");
```

## Configuration

### Database Configuration (file.properties)
```properties
MYSQL_DB_URL = jdbc:mysql://localhost:3306/mydb
DRIVER = com.mysql.cj.jdbc.Driver
USER = root
PASSWORD = your_password
```

### Connection Parameters
- **Host**: localhost
- **Port**: 3306 (default MySQL port)
- **Database**: mydb
- **SSL**: Disabled (add `?useSSL=false` to URL if needed)

## Error Handling

The application includes basic error handling for:
- Database connection failures
- SQL execution errors
- File I/O exceptions for properties loading
- Resource cleanup in finally blocks

## Best Practices Implemented

- **DataSource Usage**: Using MySQL DataSource instead of DriverManager
- **PreparedStatement**: Using parameterized queries to prevent SQL injection
- **Resource Management**: Proper closing of connections, statements, and result sets
- **Transaction Management**: Using transactions for batch operations
- **Configuration Externalization**: Database settings in external properties file

## Known Issues

1. **Batch Update Method**: The `updateEmails()` method has implementation issues with PreparedStatement reuse
2. **Connection Pooling**: Single connection usage - consider implementing connection pooling for production
3. **Error Recovery**: Limited rollback handling in transaction operations

## Future Enhancements

- Implement connection pooling (HikariCP, c3p0)
- Add logging framework (Log4j, SLF4J)
- Implement proper exception handling and custom exceptions
- Add unit tests with JUnit
- Implement delete and update operations for individual contacts
- Add input validation and data sanitization
- Implement pagination for large result sets
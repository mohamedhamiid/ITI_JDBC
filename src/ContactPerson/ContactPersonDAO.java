package ContactPerson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactPersonDAO {
    private Connection conn;
    private Statement stmt;
    private String url;
    private String username;
    private String password;

    public ContactPersonDAO(String path, String url, String username, String password) {
        try {
            this.url = url;
            this.username = username;
            this.password = password;
            Class.forName(path);
            if (connect()) {
                System.out.println("Connected successfully to " + conn.toString());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void closeConnection() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean connect() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private ContactPerson createContactPerson(ResultSet resultSet) {
        ContactPerson result = new ContactPerson();
        try {
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("name"));
            result.setNickName(resultSet.getString("nickname"));
            result.setAddress(resultSet.getString("address"));
            result.setHomePhone(resultSet.getString("homePhone"));
            result.setWorkPhone(resultSet.getString("workPhone"));
            result.setCellphone(resultSet.getString("cellphone"));
            result.setMail(resultSet.getString("mail"));
            result.setBirthdate(resultSet.getDate("birthdate"));
            result.setWebsite(resultSet.getString("website"));
            result.setProfession(resultSet.getString("profession"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public boolean createContactTable() {
        try {
            if (connect()) {
                stmt = conn.createStatement();
                // MySQL-compatible table creation SQL
                String createTableSQL = "CREATE TABLE IF NOT EXISTS ContactPerson (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "nickname VARCHAR(50), " +
                        "address VARCHAR(200), " +
                        "homePhone VARCHAR(20), " +
                        "workPhone VARCHAR(20), " +
                        "cellphone VARCHAR(20), " +
                        "mail VARCHAR(100), " +
                        "birthdate DATE, " +
                        "website VARCHAR(100), " +
                        "profession VARCHAR(100)" +
                        ")";

                stmt.executeUpdate(createTableSQL);
                System.out.println("Table 'ContactPerson' created successfully or already exists.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ContactPerson> getContacts() {
        List<ContactPerson> contacts = new ArrayList<>();
        try {
            if (connect()) {
                stmt = conn.createStatement();
                String selectSQL = "SELECT * FROM ContactPerson";
                ResultSet resultSet = stmt.executeQuery(selectSQL);

                while (resultSet.next()) {
                    ContactPerson contact = createContactPerson(resultSet);
                    contacts.add(contact);
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<ContactPerson> getContactsForName(String name) {
        List<ContactPerson> contacts = new ArrayList<>();
        try {
            if (connect()) {
                String selectSQL = "SELECT * FROM ContactPerson WHERE name LIKE ?";
                PreparedStatement pstmt = conn.prepareStatement(selectSQL);
                pstmt.setString(1, "%" + name + "%");
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    ContactPerson contact = createContactPerson(resultSet);
                    contacts.add(contact);
                }
                resultSet.close();
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public boolean insertContactPerson(ContactPerson person) {
        try {
            if (connect()) {
                String insertSQL = "INSERT INTO ContactPerson (name, nickname, address, homePhone, workPhone, cellphone, mail, birthdate, website, profession) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertSQL);

                pstmt.setString(1, person.getName());
                pstmt.setString(2, person.getNickName());
                pstmt.setString(3, person.getAddress());
                pstmt.setString(4, person.getHomePhone());
                pstmt.setString(5, person.getWorkPhone());
                pstmt.setString(6, person.getCellphone());
                pstmt.setString(7, person.getMail());
                pstmt.setDate(8,
                        person.getBirthdate() != null ? new java.sql.Date(person.getBirthdate().getTime()) : null);
                pstmt.setString(9, person.getWebsite());
                pstmt.setString(10, person.getProfession());

                int rowsAffected = pstmt.executeUpdate();
                pstmt.close();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

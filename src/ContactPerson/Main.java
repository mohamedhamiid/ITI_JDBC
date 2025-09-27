package ContactPerson;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ContactPersonDAO Test Application ===\n");

        // Database connection parameters for Oracle Database (SQL Developer)
        String driverPath = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "";

        ContactPersonDAO dao = null;

        try {
            // Initialize DAO
            System.out.println("1. Initializing ContactPersonDAO...");
            dao = new ContactPersonDAO(driverPath, url, username, password);

            // Test 1: Create table
            System.out.println("\n2. Creating contacts table...");
            boolean tableCreated = dao.createContactTable();
            if (tableCreated) {
                System.out.println("✓ Table created successfully!");
            } else {
                System.out.println("✗ Failed to create table");
            }

            // Test 2: Create test contacts
            System.out.println("\n3. Inserting test contacts...");

            // Create first contact
            ContactPerson contact1 = new ContactPerson();
            contact1.setName("Ahmed Ali");
            contact1.setNickName("Ahmed");
            contact1.setAddress("123 Cairo Street, Egypt");
            contact1.setHomePhone("02-1234567");
            contact1.setWorkPhone("02-7654321");
            contact1.setCellphone("010-12345678");
            contact1.setMail("ahmed.ali@email.com");
            contact1.setWebsite("www.ahmed-ali.com");
            contact1.setProfession("Software Engineer");

            boolean inserted1 = dao.insertContactPerson(contact1);
            System.out.println("Contact 1 inserted: " + (inserted1 ? "✓ Success" : "✗ Failed"));

            // Create second contact
            ContactPerson contact2 = new ContactPerson();
            contact2.setName("Fatima Hassan");
            contact2.setNickName("Fatima");
            contact2.setAddress("456 Alexandria Avenue, Egypt");
            contact2.setHomePhone("03-9876543");
            contact2.setCellphone("011-87654321");
            contact2.setMail("fatima.hassan@email.com");
            contact2.setProfession("Doctor");

            boolean inserted2 = dao.insertContactPerson(contact2);
            System.out.println("Contact 2 inserted: " + (inserted2 ? "✓ Success" : "✗ Failed"));

            // Test 3: Retrieve all contacts
            System.out.println("\n4. Retrieving all contacts...");
            List<ContactPerson> allContacts = dao.getContacts();
            System.out.println("Found " + allContacts.size() + " contacts:");

            for (ContactPerson contact : allContacts) {
                System.out.println("- ID: " + contact.getId() +
                        ", Name: " + contact.getName() +
                        ", Email: " + contact.getMail() +
                        ", Profession: " + contact.getProfession());
            }

            // Test 4: Search contacts by name
            System.out.println("\n5. Searching contacts with name containing 'Ahmed'...");
            List<ContactPerson> searchResults = dao.getContactsForName("Ahmed");
            System.out.println("Found " + searchResults.size() + " contacts:");

            for (ContactPerson contact : searchResults) {
                System.out.println("- Name: " + contact.getName() +
                        ", Nickname: " + contact.getNickName() +
                        ", Phone: " + contact.getCellphone());
            }

            System.out.println("\n=== Test completed successfully! ===");

        } catch (Exception e) {
            System.err.println("Error during testing: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up
            if (dao != null) {
                try {
                    dao.closeConnection();
                    System.out.println("\nConnection closed successfully.");
                } catch (Exception e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}

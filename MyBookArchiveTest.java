import java.sql.*;

// JDK 1.7 and above

public class MyBookArchiveTest {   // Save as "JdbcSelectTest.java"
    public static void main(String[] args) {
        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bookArchive?useSSL=false&serverTimezone=UTC",
                        "root", "Admin88");

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement statement = conn.createStatement();
        ) {
            // Step 3: Execute a SQL SELECT query, the query result
            //  is returned in a 'ResultSet' object.
//            String strInsert = "INSERT INTO books " +
//                    "VALUES (5, 'Stephen', 'King', 'A halálsoron', 0);";
            String strSelect = "select * from books";
            System.out.println("The SQL query is: " + strSelect); // Echo For debugging
            System.out.println();

//            int resultSetInsert = statement.executeUpdate(strInsert);
            ResultSet resultSetSelect = statement.executeQuery(strSelect);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
//            System.out.println("Inserted rows: " + resultSetInsert);
            System.out.println("The records selected are:");
            int rowCount = 0;
            while (resultSetSelect.next()) {   // Move the cursor to the next row, return false if no more row
                Integer ID = Integer.parseInt(resultSetSelect.getString("ID"));
                String AuthorFirstName = resultSetSelect.getString("AuthorFirstName");
                String AuthorLastName = resultSetSelect.getString("AuthorLastName");
                String BookTitle = resultSetSelect.getString("BookTitle");
                System.out.println(ID + ", " + AuthorFirstName + ", " + AuthorLastName + ", " + BookTitle);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Step 5: Close the resources - Done automatically by try-with-resources
    }
}
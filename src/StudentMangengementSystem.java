import java.sql.*;
import java.util.Scanner;

public class StudentMangengementSystem {
    static final String URL = "jdbc:mysql://localhost:3306/student";
    static final String USER = "root";
    static final String PASS = "Root@123"; // change password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Choose: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Marks: ");
                        int marks = sc.nextInt();

                        PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO students(name, marks) VALUES (?, ?)");
                        ps.setString(1, name);
                        ps.setInt(2, marks);
                        ps.executeUpdate();
                        System.out.println("Student Added!");
                        break;

                    case 2:
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM students");

                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + " "
                                    + rs.getString("name") + " "
                                    + rs.getInt("marks"));
                        }
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        int uid = sc.nextInt();
                        System.out.print("New Marks: ");
                        int newMarks = sc.nextInt();

                        PreparedStatement ps2 = con.prepareStatement(
                            "UPDATE students SET marks=? WHERE id=?");
                        ps2.setInt(1, newMarks);
                        ps2.setInt(2, uid);
                        ps2.executeUpdate();
                        System.out.println("Updated!");
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        int did = sc.nextInt();

                        PreparedStatement ps3 = con.prepareStatement(
                            "DELETE FROM students WHERE id=?");
                        ps3.setInt(1, did);
                        ps3.executeUpdate();
                        System.out.println("Deleted!");
                        break;

                    case 5:
                        System.exit(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
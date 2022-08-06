package Models;

import java.sql.*;

public class sqlitetest {
    public Connection getConnection() {
   String connect_string = "jdbc:sqlite:test.db";

        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args) {
        String connect_string = "jdbc:sqlite:test.db";

        Connection connection = null;


        String query = "SELECT * FROM attendance";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Raihan Miraj\\eclipse-workspace\\incoursemanagement\\src\\main\\java\\Models/datas.db");
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                System.out.println(rs.getString("id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
e.printStackTrace();
        }
    }

}

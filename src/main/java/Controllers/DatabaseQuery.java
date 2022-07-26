package Controllers;

import java.sql.*;

public class DatabaseQuery {
    private static String databaseHost = "jdbc:mysql://localhost/incoursemanagement";
    private static String databaseUser = "root";
    private static String databasePass = "";
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(databaseHost,databaseUser,databasePass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ResultSet get(String query){
//        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try{
//
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int insert(String query){
//        Connection connection = null;
        int resultSet = 0;
        PreparedStatement preparedStatement = null;
        try {
//            connection = DriverManager.getConnection(databaseHost,databaseUser,databasePass);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return resultSet;
     }

     public static int update(String query){
//         Connection connection = null;
         int y = 0;
          PreparedStatement preparedStatement = null;
         try {
//             connection = DriverManager.getConnection(databaseHost,databaseUser,databasePass);
             Statement stmt = connection.createStatement();
             y= stmt.executeUpdate(query);

         } catch (SQLException e) {
             e.printStackTrace();
         }

         return y;
     }
     public static int updateOrinsert(String Updatequery, String insertQuery){
         int x = 0;
       if(update(Updatequery)==1){
           x=1;
       } else if (insert(insertQuery)==1) {
           x=2;
       }

        return x;
     }

//     public static int insert(){
//         Connection connection = null;
//         int y = 0;
//         PreparedStatement preparedStatement = null;
//         try {
//             connection = DriverManager.getConnection(databaseHost,databaseUser,databasePass);
//             Statement stmt = connection.createStatement();
//            userInsert = connection.prepareStatement("INSERT INTO student (Name,Email,Password,Year,Semester,Roll,Registration) VALUES (?,?,?,?,?,?,? )");
//            userInsert.setString(1,Name);
//            userInsert.setString(2,Email);
//            userInsert.setString(3,Password);
//            userInsert.setString(4, String.valueOf(year));
//            userInsert.setString(5, String.valueOf(semester));
//            userInsert.setString(6, String.valueOf(roll));
//            userInsert.setString(7,registration);
//            userInsert.executeUpdate();
//        }catch (Exception e){
//
//        }
//     }


    public static int insertGetId(String query) {
      //"INSERT INTO `unicodeinfo`(`id`, `UserName`, `Language`, `Message`) VALUES (?,?,?,?)";
        int primkey = 0 ;
//        Connection connection = null;
        int y = 0;

        try {
//            connection = DriverManager.getConnection(databaseHost,databaseUser,databasePass);



            String columnNames[] = new String[] { "id" };

            PreparedStatement pstmt = connection.prepareStatement( query, columnNames );


            if (pstmt.executeUpdate() > 0) {
                // Retrieves any auto-generated keys created as a result of executing this Statement object
                java.sql.ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if ( generatedKeys.next() ) {
                    primkey = generatedKeys.getInt(1);
                }
            }
            System.out.println("Record updated with id = "+primkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primkey;
    }


    public static int delete(String query){
//        Connection connection = null;
        int y = 0;
        PreparedStatement preparedStatement = null;
        try {
//            connection = DriverManager.getConnection(databaseHost,databaseUser,databasePass);
            Statement stmt = connection.createStatement();
            y= stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return y;
    }
}

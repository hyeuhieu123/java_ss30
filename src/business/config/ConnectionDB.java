package business.config;

import java.sql.Connection;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/demo1?createDatabaseIfNotExist=true";
    private static final String USERNAME = "root";
    private  static final  String PASSWORD = "hieu2005";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("ket noi thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            if (getConnection() != null) {
                getConnection().close();
                System.out.println("mat ket noi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

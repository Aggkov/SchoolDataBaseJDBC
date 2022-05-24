package dao;

import java.sql.*;

public class ConnectDao {


    private static ConnectDao connect_instance; // Singleton
    private Connection conn;
    private PreparedStatement preparedStatement;

    private ConnectDao() {

    }

    public static ConnectDao getConnect_instance() {
        if(connect_instance == null) {
            connect_instance = new ConnectDao();
        }
        return connect_instance;
    }

    public Connection getSQLConnection() {
        try {
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmodel",
                    "javauser", "dn2dggq");

        } catch(SQLException e) {
            System.out.println(e);
        }
        return conn;
    }

    public void closeSQLConnection() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void closeStatement() {
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}

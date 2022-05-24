package dao.queries;

import dao.ConnectDao;

import java.sql.*;

public class CourseQuery {

    ConnectDao connect_instance = ConnectDao.getConnect_instance();
    Connection conn =  connect_instance.getSQLConnection();

    public void showAllCourses() {

        String courseQuery = "SELECT * FROM course";

        try {
            PreparedStatement pstm = conn.prepareStatement(courseQuery);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                System.out.print(rs.getString("title") + ": ");
                System.out.print(rs.getString("stream") + " | ");
                System.out.print(rs.getString("type") + " | ");
                System.out.print(rs.getDate("start_date") + " ");
                System.out.println(rs.getDate("end_date"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        connect_instance.closeStatement();
    }
}

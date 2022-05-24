package dao.queries;

import dao.ConnectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerQuery {

    ConnectDao connect_instance = ConnectDao.getConnect_instance();
    Connection conn =  connect_instance.getSQLConnection();

    public void showAllTrainers() {

        String trainersQuery = "SELECT * FROM trainer";

        try {
            PreparedStatement pstm = conn.prepareStatement(trainersQuery);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getInt("trainer_id") + ": ");
                System.out.print(rs.getString("first_name") + " ");
                System.out.print(rs.getString("last_name") + " | ");
                System.out.println(rs.getString("subject"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        connect_instance.closeStatement();
    }

    public void showAllTrainersPerCourse() {

        String trainersPerCourseQuery = "SELECT c.title AS Title, c.stream AS Stream, t.first_name AS Firstname, t.last_name AS Lastname " +
                "FROM trainer t " +
                "   JOIN course c ON t.course_no = c.course_id ";

        try {
            PreparedStatement pstm = conn.prepareStatement(trainersPerCourseQuery);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString("Title") + ": ");
                System.out.print(rs.getString("Stream") + " --> ");
                System.out.print(rs.getString("Firstname") + " ");
                System.out.println(rs.getString("Lastname"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        connect_instance.closeStatement();
    }
}

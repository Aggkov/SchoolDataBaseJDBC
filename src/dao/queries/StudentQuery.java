package dao.queries;

import dao.ConnectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentQuery {

    ConnectDao connect_instance = ConnectDao.getConnect_instance();
    Connection conn = connect_instance.getSQLConnection();

    public void showAllStudents() {

        String studentsQuery = "SELECT * FROM student";

        try {
            PreparedStatement pstm = conn.prepareStatement(studentsQuery);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getInt("student_id") + ": ");
                System.out.print(rs.getString("first_name") + " ");
                System.out.print(rs.getString("last_name") + " | ");
                System.out.println(rs.getDate("date_of_birth"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        connect_instance.closeStatement();
    }

    public void showStudentsPerCourse() {

        String studentsPerCourse = "SELECT DISTINCT c.title AS Title , c.stream AS Stream , s.first_name AS Firstname, s.last_name AS Lastname " +
                "FROM assignment_student_course a_s_c " +
                "   JOIN course c ON a_s_c.course_code = c.course_id " +
                "   JOIN student s ON a_s_c.student_code = s.student_id " +
                "ORDER BY c.title ,s.student_id";

        try {
            PreparedStatement pstm = conn.prepareStatement(studentsPerCourse);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                System.out.print(rs.getString("title") + ": ");
                System.out.print(rs.getString("stream") + " | ");
                System.out.print(rs.getString("Firstname") + " | ");
                System.out.println(rs.getString("Lastname") + " ");
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }

        connect_instance.closeStatement();
    }

    public void showStudentsEnrolledInMultipleCourses() {

        String studentsInMultipleCoursesQuery = "SELECT s.student_id AS Student_id, s.last_name AS Lastname, s.first_name AS Firstname\n" +
                "FROM student_course s_c\n" +
                "\tJOIN course c ON s_c.course_code = c.course_id\n" +
                "    JOIN student s ON s_c.student_code = s.student_id\n" +
                "GROUP BY s.student_id\n" +
                "HAVING COUNT(*) > 1";

        try {
            PreparedStatement pstm = conn.prepareStatement(studentsInMultipleCoursesQuery);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                System.out.print(rs.getInt("Student_id") + ": ");
                System.out.print(rs.getString("Lastname") + " ");
                System.out.println(rs.getString("Firstname") + " ");
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

        connect_instance.closeStatement();
    }
}

package dao.queries;

import dao.ConnectDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignmentQuery {

    ConnectDao connect_instance = ConnectDao.getConnect_instance();
    Connection conn = connect_instance.getSQLConnection();

    public void showAllAssignments() {

        String assignmentQuery = "SELECT DISTINCT title AS Title " + ", sub_date_time AS Submission_Date, oral_mark, total_mark " +
                "FROM assignment ";

        try {
            PreparedStatement pstm = conn.prepareStatement(assignmentQuery);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString("Title") + ": ");
                System.out.print(rs.getDate("Submission_date") + " | ");
                System.out.print(rs.getInt("oral_mark") + " | ");
                System.out.println(rs.getInt("total_mark"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        connect_instance.closeStatement();
    }

    public void showAllAssignmentsPerCourse() {

        String assignmentPerCourseQuery = "SELECT c.title AS Title, c.stream AS Stream, a.title AS Assignment_Title, a.sub_date_time AS Submission_Date\n" +
                "FROM assignment_student_course a_s_c \n" +
                "\tJOIN course c ON a_s_c.course_code = c.course_id\n" +
                "    JOIN assignment a ON a_s_c.assignment_code = a.assignment_id";

        try {
            PreparedStatement pstm = conn.prepareStatement(assignmentPerCourseQuery);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString("Title") + ": ");
                System.out.print(rs.getString("Stream") + " ---> ");
                System.out.print(rs.getString("Assignment_Title") + " ");
                System.out.println(rs.getDate("Submission_date") + " | ");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        connect_instance.closeStatement();
    }

    public void showAllAssignmentsPerCoursePerStudent() {

        String assignmentPerCoursePerStudentQuery = "SELECT c.title AS Title, c.stream AS Stream, s.first_name AS Firstname, s.last_name AS Lastname, a.title AS Assignment_Title\n" +
                "FROM assignment_student_course a_s_c\n" +
                "\tJOIN course c ON a_s_c.course_code = c.course_id\n" +
                "\tJOIN assignment a  ON a_s_c.assignment_code = a.assignment_id\n" +
                "\tJOIN student s ON a_s_c.student_code = s.student_id\n" +
                "ORDER BY  c.title, s.student_id";

        try {
            PreparedStatement pstm = conn.prepareStatement(assignmentPerCoursePerStudentQuery);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString("Title") + ": ");
                System.out.print(rs.getString("Stream") + " ---> ");
                System.out.print(rs.getString("Firstname") + " | ");
                System.out.print(rs.getString("Lastname") + " | ");
                System.out.println(rs.getString("Assignment_Title") + " ");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        connect_instance.closeStatement();
    }
}

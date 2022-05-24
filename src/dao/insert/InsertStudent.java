package dao.insert;

import dao.ConnectDao;
import utils.UserInput;
import utils.Queries;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsertStudent {


    ConnectDao connect_instance = ConnectDao.getConnect_instance();
    Connection conn = connect_instance.getSQLConnection();


    public int insertStudent() {

        String firstName = UserInput.readString("Please enter student's firstname");
        String lastName = UserInput.readString("Please enter student's lastName");
        LocalDate dob = UserInput.readDate("Please enter student's date of birth");

        try {
            PreparedStatement pstm = conn.prepareStatement(Queries.INSERT_INTO_STUDENT, Statement.RETURN_GENERATED_KEYS);

            // check ResultSet if student already exists
            ResultSet resultSet = queryStudentByFullName(firstName, lastName);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                pstm.setString(1, firstName);
                pstm.setString(2, lastName);
                pstm.setDate(3, Date.valueOf(dob));

                pstm.executeUpdate();
            }

            // get his key after insertion
            ResultSet genKeys = pstm.getGeneratedKeys();
            genKeys.next();
            return genKeys.getInt(1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        connect_instance.closeStatement();
        return -2;
    }

    // check if student already exists -- Returns a resultSet that is checked by insertStudent method
    private ResultSet queryStudentByFullName(String firstName, String lastName) {

        String query = Queries.QUERY_STUDENT_BY_FULLNAME;
        try {
            PreparedStatement pstm = conn.prepareStatement(query);

            pstm.setString(1, firstName);
            pstm.setString(2, lastName);

            return pstm.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertStudentIntoCourse() {
        try {
            conn.setAutoCommit(false);
            // get Student's generated Key and User Input for Course
            int studentKey = insertStudent();
            int courseChoice = UserInput.readInt("Please make your choice of the following options -->" +
                    UserInput.getOptionsNumbersAndNames(Queries.COURSES),
                    UserInput.convertStringOptionsToInt(Queries.COURSES));

            // Get chosen Course ID
            InsertCourse insertCourse = new InsertCourse();
            int findCourseById = insertCourse.queryCourseByID(courseChoice);

            // Insert Student to Course
            String insertStudentIntoCourse = Queries.INSERT_STUDENT_TO_COURSE;
            PreparedStatement pstm = conn.prepareStatement(insertStudentIntoCourse);
            pstm.setInt(1, studentKey);
            pstm.setInt(2, findCourseById);

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("insert student to course failed");
            }

            // Insert course_id, student_id and assignment_id to table
            insertAssignmentIntoStudentIntoCourse(findCourseById, studentKey);

        } catch (SQLException e) {
            System.out.println("something went wrong" + e);
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("reset auto commit failed");
            }
        }
        connect_instance.closeStatement();
    }

    public void insertAssignmentIntoStudentIntoCourse(int findCourseById, int studentKey) {
        try {
            PreparedStatement pstm = conn.prepareStatement(Queries.INSERT_TO_ASSIGNMENT_STUDENT_COURSE);

            // insert all assignments with their respective ID's, then newly created student ID and course ID to table
            List<Integer> assignmentsID = queryAssignmentsByCourseID(findCourseById);
            for(int i = 0; i < assignmentsID.size(); i++) {
                pstm.setInt(1, findCourseById);
                pstm.setInt(2, assignmentsID.get(i));
                pstm.setInt(3, studentKey);

                int affectedRows = pstm.executeUpdate();
                if (affectedRows == 1) {
                    conn.commit();
                } else {
                    throw new SQLException("insert student to course to assignment failed");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        connect_instance.closeStatement();
    }

    public List<Integer> queryAssignmentsByCourseID(int findCourseById) {

        String query = Queries.QUERY_ASSIGNMENTS_BY_COURSE_ID;

        try {
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, findCourseById);
            ResultSet resultSet = pstm.executeQuery();

            // put ResultSets assignment_id's into an arraylist of Integers
            List<Integer> assignmentList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("assignment_id");
                assignmentList.add(id);
            }
            return assignmentList;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}





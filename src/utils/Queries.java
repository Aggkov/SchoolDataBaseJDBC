package utils;


import java.util.ArrayList;
import java.util.Arrays;

public class Queries {

    // Student Queries
    public static final String QUERY_STUDENT_BY_FULLNAME = "SELECT " + "student_id " + " FROM " +
            "student" + " WHERE " + "first_name = ?" + " AND last_name = ?";

    // trainer Queries
    public static final String QUERY_TRAINER_BY_FULLNAME = "SELECT " + "trainer_id " + " FROM " +
            "trainer" + " WHERE " + "first_name = ?" + " AND last_name = ?";

    // Course Queries
    public static final String QUERY_COURSE_BY_ID = "SELECT course_id FROM course WHERE course_id = ?";
    public static final String QUERY_COURSE_BY_TITLE = "SELECT title FROM course WHERE title = ?";

    // Assignment Queries
    public static final String QUERY_ASSIGNMENTS_BY_COURSE_ID = "SELECT DISTINCT a.assignment_id " +
            " FROM assignment a " +
            " JOIN assignment_student_course a_s_c ON a_s_c.assignment_code = a.assignment_id \n" +
            " WHERE course_code = ?";

    // INSERTS

    // Student Inserts
    public static final String INSERT_INTO_STUDENT = "INSERT INTO student (first_name, last_name, date_of_birth)" +
            " VALUES(?, ?, ?)";
    public static final String INSERT_STUDENT_TO_COURSE = "INSERT INTO student_course (student_code, course_code) " +
            "   VALUES (?, ?);";

    // Trainer inserts
    public static final String INSERT_INTO_TRAINER = "INSERT INTO trainer (first_name, last_name, subject, course_no)" +
           " VALUES (?, ?, ?, ?)";


    public static final String INSERT_TO_ASSIGNMENT_STUDENT_COURSE = "INSERT INTO assignment_student_course (course_code, assignment_code, student_code) " +
            " VALUES(?, ?, ?)";

    // All courses
    public static ArrayList<String> COURSES = new ArrayList<>(Arrays.asList("Java", "JavaScript", "C"));
    public static final String INSERT_INTO_COURSE = "INSERT INTO course (title, stream, type, start_date, end_date) " +
            " VALUES (?, ?, ?, ?, ?)";


}

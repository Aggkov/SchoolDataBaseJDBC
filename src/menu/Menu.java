package menu;

import dao.ConnectDao;
import dao.insert.InsertCourse;
import dao.insert.InsertStudent;
import dao.insert.InsertTrainer;
import dao.queries.AssignmentQuery;
import dao.queries.CourseQuery;
import dao.queries.StudentQuery;
import dao.queries.TrainerQuery;
import utils.UserInput;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu {
    private static Menu menu_Instance; //singleton
    ConnectDao connect_instance = ConnectDao.getConnect_instance();

    private Menu() {

    }

    public static Menu getMenu_Instance (){
        if ( menu_Instance == null ) {
            menu_Instance = new Menu();
        }
        menu_Instance.menu();
        return menu_Instance;
    }


    private void menu() {

        StudentQuery studentQuery = new StudentQuery();
        TrainerQuery trainerQuery = new TrainerQuery();
        CourseQuery courseQuery = new CourseQuery();
        AssignmentQuery assignmentQuery = new AssignmentQuery();

        InsertStudent insertStudent = new InsertStudent();
        InsertCourse insertCourse = new InsertCourse();
        InsertTrainer insertTrainer = new InsertTrainer();

        boolean flag = false;

        while (!flag) {

            int userOptionsLevelOne[] = {1, 2, 3};
            ArrayList<String> menuItems = new ArrayList<>(Arrays.asList("Query", "Insert", "Exit"));

            int choiceLevelOne = UserInput.readInt("Would you like to query the DB or insert data?" +
                            UserInput.getOptionsNumbersAndNames(menuItems),
                    userOptionsLevelOne);

            if (choiceLevelOne == 3) {
                connect_instance.closeSQLConnection();
                break;
            }

            switch (choiceLevelOne) {
                case 1:// Queries

                    ArrayList<String> queryItems = new ArrayList<>(Arrays.asList("Student Query", "Trainer Query", "Course Query", "Assignment Query", "Back"));
                    int[] userQueryOptions = {1, 2, 3, 4, 5};
                    int userQueryOption = UserInput.readInt("Make your choice for querying -->" + UserInput.getOptionsNumbersAndNames(queryItems), userQueryOptions);

                    if (userQueryOption == 5) {
                        break;
                    }

                    switch (userQueryOption) {
                        case 1:// Student Queries

                            ArrayList<String> studentQueryItems = new ArrayList<>(Arrays.asList("Show all Students", "Show all Students per Course", "Show Students enrolled in multiple courses", "Back"));
                            int[] studentQueryOptions = {1, 2, 3, 4};
                            int studentQueryOption = UserInput.readInt("\nMake your choice for querying\n" + UserInput.getOptionsNumbersAndNames(studentQueryItems), studentQueryOptions);

                            if (studentQueryOption == 4) {
                                break;
                            }

                            switch (studentQueryOption) {

                                case 1:
                                    studentQuery.showAllStudents();
                                    break;
                                case 2:
                                    studentQuery.showStudentsPerCourse();
                                    break;
                                case 3:
                                    studentQuery.showStudentsEnrolledInMultipleCourses();
                                    break;
                            }
                            break;

                        case 2:

                            ArrayList<String> trainerQueryItems = new ArrayList<>(Arrays.asList("Show all Trainers", "Show all Trainers per Course", "Back"));
                            int[] trainerQueryOptions = {1, 2, 3};
                            int trainerQueryOption = UserInput.readInt("\nMake your choice for querying\n" + UserInput.getOptionsNumbersAndNames(trainerQueryItems), trainerQueryOptions);

                            if (trainerQueryOption == 3) {
                                break;
                            }
                            switch (trainerQueryOption) {
                                case 1:
                                    trainerQuery.showAllTrainers();
                                    break;
                                case 2:
                                    trainerQuery.showAllTrainersPerCourse();
                                    break;
                                case 3:
                                    studentQuery.showStudentsEnrolledInMultipleCourses();
                                    break;
                            }
                            break;

                        case 3:

                            ArrayList<String> courseQueryItems = new ArrayList<>(Arrays.asList("Show all Courses", "Back"));
                            int[] courseQueryOptions = {1, 2};
                            int courseQueryOption = UserInput.readInt("\nMake your choice for querying\n" + UserInput.getOptionsNumbersAndNames(courseQueryItems), courseQueryOptions);

                            if (courseQueryOption == 2) {
                                break;
                            }
                            switch (courseQueryOption) {
                                case 1:
                                    courseQuery.showAllCourses();
                                    break;
                            }
                            break;

                        case 4:

                            ArrayList<String> assignmentQueryItems = new ArrayList<>(Arrays.asList("Show all Assignments", "Show all Assignments per Course",
                                    "show All Assignments Per Course Per Student", "Back"));
                            int[] assignmentQueryOptions = {1, 2, 3, 4};
                            int queryOption = UserInput.readInt("\nMake your choice for querying\n" + UserInput.getOptionsNumbersAndNames(assignmentQueryItems), assignmentQueryOptions);

                            if (queryOption == 4) {
                                break;
                            }
                            switch (queryOption) {
                                case 1:
                                    assignmentQuery.showAllAssignments();
                                    break;
                                case 2:
                                    assignmentQuery.showAllAssignmentsPerCourse();
                                    break;
                                case 3:
                                    assignmentQuery.showAllAssignmentsPerCoursePerStudent();
                            }
                            break;
                    }
                    break;

                case 2: // Inserts

                    ArrayList<String> insertItems = new ArrayList<>(Arrays.asList("Student Insert", "Trainer Insert", "Course Insert", "Back"));
                    int[] userInsertOptions = {1, 2, 3, 4};
                    int insertOption = UserInput.readInt("Make your choice for inserting data " + UserInput.getOptionsNumbersAndNames(insertItems), userInsertOptions);

                    if (insertOption == 4) {
                        break;
                    }
                    switch (insertOption) {
                        case 1:
                            insertStudent.insertStudentIntoCourse();
                            break;
                        case 2:
                            insertTrainer.insertTrainer();
                        case 3:
                            insertCourse.insertCourse();
                    }
                    break;
            }
        }
    }
}













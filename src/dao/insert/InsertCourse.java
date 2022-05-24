package dao.insert;

import dao.ConnectDao;
import utils.Queries;
import utils.UserInput;

import java.sql.*;
import java.time.LocalDate;

public class InsertCourse {

    ConnectDao connect_instance = ConnectDao.getConnect_instance();
    Connection conn = connect_instance.getSQLConnection();

    public int insertCourse() {

        String title = UserInput.readString("Please enter course's title");
        String stream = UserInput.readString("Please enter course's stream");
        String type = UserInput.readString("Please enter course's type");
        LocalDate start_date = UserInput.readDate("Please enter course's start date");
        LocalDate end_date = UserInput.readDate("Please enter course's end date");

        try {
            PreparedStatement pstm = conn.prepareStatement(Queries.INSERT_INTO_COURSE, Statement.RETURN_GENERATED_KEYS);

            // check ResultSet if course already exists
            ResultSet resultSet = queryCourseByTitle(title);
            if (resultSet.next()) {
                return resultSet.getInt(1);

            } else {
                pstm.setString(1, title);
                pstm.setString(2, stream);
                pstm.setString(3, type);
                pstm.setDate(4, Date.valueOf(start_date));
                pstm.setDate(5, Date.valueOf(end_date));

                pstm.executeUpdate();
                Queries.COURSES.add(stream);
            }

            // get key after insertion
            ResultSet genKeys = pstm.getGeneratedKeys();
            genKeys.next();
            return genKeys.getInt(1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        connect_instance.closeStatement();
        return -2;
    }

    public int queryCourseByID(int id) {

        String queryCourseById = Queries.QUERY_COURSE_BY_ID;

        try {
            PreparedStatement pstm = conn.prepareStatement(queryCourseById);
            pstm.setInt(1, id);
            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        connect_instance.closeStatement();
        return -2;
    }

    private ResultSet queryCourseByTitle (String title) {

        String query = Queries.QUERY_COURSE_BY_TITLE;
        try {
            PreparedStatement pstm = conn.prepareStatement(query);

            pstm.setString(1, title);

            return pstm.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        }
        connect_instance.closeStatement();
        return null;
    }
}

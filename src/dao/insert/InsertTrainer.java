package dao.insert;

import dao.ConnectDao;
import utils.Queries;
import utils.UserInput;

import java.sql.*;

public class InsertTrainer {

    ConnectDao connect_instance = ConnectDao.getConnect_instance();
    Connection conn = connect_instance.getSQLConnection();

    public int insertTrainer() {
        String insert = Queries.INSERT_INTO_TRAINER;

        String firstName = UserInput.readString("Please enter trainer's firstname");
        String lastName = UserInput.readString("Please enter trainer's lastName");
        String subject = UserInput.readString("Please enter trainer's subject");
        int courseChoice = UserInput.readInt("Please make your choice for trainer's subject -->" +
                UserInput.getOptionsNumbersAndNames(Queries.COURSES), UserInput.convertStringOptionsToInt(Queries.COURSES));

        try {
            PreparedStatement pstm = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            // check ResultSet if trainer already exists
            ResultSet resultSet = queryTrainerByFullName(firstName, lastName);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                pstm.setString(1, firstName);
                pstm.setString(2, lastName);
                pstm.setString(3, subject);
                pstm.setInt(4, courseChoice);

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

    private ResultSet queryTrainerByFullName(String firstName, String lastName) {

        String query = Queries.QUERY_TRAINER_BY_FULLNAME;
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
}

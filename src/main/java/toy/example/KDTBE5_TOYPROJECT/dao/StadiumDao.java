package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StadiumDao {
//    private Connection connection = DBConnection.getInstance();
    private Connection connection;
    private static final StadiumDao instance = new StadiumDao();

    private StadiumDao() {
        connection = DBConnection.getInstance();
    }

    public static StadiumDao getInstance() {
        return instance;
    }

    // 경기장 등록
    public int insert(Stadium stadium) throws SQLException {
        String query = "INSERT INTO stadium_tb (name, created_at) VALUES (?, NOW())";
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, stadium.getName());
            result =  statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StadiumDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}

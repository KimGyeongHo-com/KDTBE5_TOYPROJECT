package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PlayerDao {

    Connection connection = DBConnection.getInstance();
    private static PlayerDao playerDao = new PlayerDao();

    private PlayerDao(){

    }
    public static PlayerDao getInstance(){
        return playerDao;
    }


    //메소드들 작성하시면 됩니다.
    public int findById(int id) {
        String query = "SELECT * FROM player WHERE id = ?";
        int rowCount = -1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                rowCount = resultSet.getInt("id");
        } catch (SQLException e) {
            Logger.getLogger("선수 select: " + e.getMessage());
        }
        return rowCount;
    }

    public int updatePlayer(int id) {
        String query = "UPDATE player SET team_id = ? WHERE id = ?";
        int rowCount = -1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNull(1, Types.INTEGER);
            statement.setInt(2, id);

            rowCount = statement.executeUpdate(); //0일때 처리를 해주면 더 구분이 될듯
        } catch (SQLException e) {
            Logger.getLogger("선수 update: " + e.getMessage());
        }
        return rowCount;
    }

}

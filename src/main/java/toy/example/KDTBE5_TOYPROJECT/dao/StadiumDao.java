package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Builder;
import lombok.Getter;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StadiumDao{
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
        String query = "INSERT INTO stadium (name, created_at) VALUES (?, NOW())";
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, stadium.getName());
            result =  statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StadiumDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    //메소드들 작성하시면 됩니다.
    public List<Stadium> getStadiumList() {
        List<Stadium> stadiumList = new ArrayList<>();
        String query = "SELECT * FROM stadium";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Stadium stadium = Stadium.builder()
                        .id(resultSet.getInt("id")) //colum명을 이렇게 직접 넣나??
                        .name(resultSet.getString("name"))
                        .created_at(resultSet.getTimestamp("created_at"))
                        .build();

                stadiumList.add(stadium);
            }
        }catch(SQLException e){
            Logger.getLogger("경기장 select: " + e.getMessage());
        }
        return stadiumList;
    }

}

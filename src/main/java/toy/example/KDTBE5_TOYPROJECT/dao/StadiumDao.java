package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class StadiumDao{

    Connection connection = DBConnection.getInstance();
    private static StadiumDao stadiumDao = new StadiumDao();

    private StadiumDao(){

    }
    public static StadiumDao getInstance(){
        return stadiumDao;
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
                        .createDate(resultSet.getTimestamp("created_at"))
                        .build();

                stadiumList.add(stadium);
            }
        }catch(SQLException e){
            Logger.getLogger("경기장 select: " + e.getMessage());
        }
        return stadiumList;
    }

}

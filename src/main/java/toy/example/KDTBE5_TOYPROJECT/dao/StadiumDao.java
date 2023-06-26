package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class StadiumDao {
    private static StadiumDao INSTANCE;
    private Connection connection;

    /* 싱글톤 패턴입니다. */
    private StadiumDao(Connection connection){
        this.connection = connection;
    }
    public static synchronized StadiumDao getInstance(){
        if(INSTANCE == null)
            INSTANCE = new StadiumDao(DBConnection.getInstance());

        return INSTANCE;
    }


    // 경기장 등록
    public int insert(Stadium stadium) throws SQLException {
        String query = "INSERT INTO stadium_tb (name, created_at) VALUES (?, NOW())";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, stadium.getName());
            return statement.executeUpdate();
        }
    }
}

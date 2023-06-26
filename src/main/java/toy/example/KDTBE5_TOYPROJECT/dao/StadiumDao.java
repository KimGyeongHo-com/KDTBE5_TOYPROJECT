package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;

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
            INSTANCE = new StadiumDao(getInstance().connection);

        return INSTANCE;
    }

    // 경기장 등록
    public void insertStadium(String name) throws SQLException {
        String query = "INSERT INTO stadium (name, create_date) VALUES (?, NOW())";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }
}

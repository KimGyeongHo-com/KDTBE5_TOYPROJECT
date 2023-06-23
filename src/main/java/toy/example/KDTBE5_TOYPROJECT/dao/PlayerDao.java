package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PlayerDao {
    private static PlayerDao INSTANCE;
    private Connection connection;

    /* 싱글톤 패턴입니다. */
    private PlayerDao(Connection connection){
        this.connection = connection;
    }
    public static synchronized PlayerDao getInstance(){
        if(INSTANCE == null)
            INSTANCE = new PlayerDao(getInstance().connection);

        return INSTANCE;
    }


    //메소드들 작성하시면 됩니다.
}

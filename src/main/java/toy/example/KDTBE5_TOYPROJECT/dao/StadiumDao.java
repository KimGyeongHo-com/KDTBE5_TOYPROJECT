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

    //메소드들 작성하시면 됩니다.
}

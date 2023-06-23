package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TeamDao {
    private static TeamDao INSTANCE;
    private Connection connection;

    /* 싱글톤 패턴입니다. */
    private TeamDao(Connection connection){
        this.connection = connection;
    }
    public static synchronized TeamDao getInstance(){
        if(INSTANCE == null)
            INSTANCE = new TeamDao(getInstance().connection);

        return INSTANCE;
    }
    //메소드들 작성하시면 됩니다.
}

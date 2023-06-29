package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ExpulsionDao {
    private static ExpulsionDao INSTANCE;

    private Connection connection;

    /* 싱글톤 패턴입니다. */
    private ExpulsionDao(Connection connection){
        this.connection = connection;
    }
    public static synchronized ExpulsionDao getInstance(){
        if(INSTANCE == null)
            INSTANCE = new ExpulsionDao(getInstance().connection);

        return INSTANCE;
    }


    //메소드들 작성하시면 됩니다.
}

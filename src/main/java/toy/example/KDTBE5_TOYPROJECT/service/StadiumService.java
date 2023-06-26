package toy.example.KDTBE5_TOYPROJECT.service;

import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;

import java.sql.SQLException;
import java.sql.Timestamp;

public class StadiumService {
    private StadiumDao stadiumDao;

    public StadiumService(StadiumDao stadiumDao) {
        this.stadiumDao = stadiumDao;
    }

    public int insertStadium(String name) {
        Stadium stadium = Stadium.builder()
                .name(name)
                .build();
        try {
            return stadiumDao.insert(stadium);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
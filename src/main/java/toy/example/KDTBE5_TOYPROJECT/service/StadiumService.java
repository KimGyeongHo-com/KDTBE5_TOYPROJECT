package toy.example.KDTBE5_TOYPROJECT.service;

import org.springframework.transaction.annotation.Transactional;
import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.dto.stadium.InsertStadiumReqDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.SQLException;

public class StadiumService {
    private StadiumDao stadiumDao;

    public StadiumService(StadiumDao stadiumDao) {
        this.stadiumDao = stadiumDao;
    }

    @Transactional
    public void insertStadium(InsertStadiumReqDTO insertStadiumReqDTO) {
        try {
            Stadium stadium = insertStadiumReqDTO.toEntity();
            int result = stadiumDao.insert(stadium);
            if (result != 1) {
                throw new RuntimeException();
            }
            System.out.println("성공");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
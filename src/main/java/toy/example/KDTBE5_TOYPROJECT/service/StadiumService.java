package toy.example.KDTBE5_TOYPROJECT.service;

import org.springframework.transaction.annotation.Transactional;
import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.dto.UserInputDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;

import java.sql.SQLException;


public class StadiumService {
    private StadiumDao stadiumDao;

    public StadiumService(StadiumDao stadiumDao) {
        this.stadiumDao = stadiumDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertStadium(UserInputDTO userInputDTO) {
        try {
            // 인자 유무 확인
            if (userInputDTO.getArguments() == null){
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            String name = userInputDTO.getArgument("name");

            Stadium stadium = new Stadium(name);

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
package toy.example.KDTBE5_TOYPROJECT.service;

import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;

import java.sql.Connection;
import java.sql.SQLException;

import db.DBConnection;

public class StadiumService {
    private StadiumDao stadiumDao;

    public StadiumService(StadiumDao stadiumDao) {
        this.stadiumDao = stadiumDao;
    }

    public void insertStadium(String name) {
        try (Connection connection = DBConnection.getInstance()) {
            // null일 경우
            if (connection == null) {
                throw new RuntimeException("연결 실패");
            }

            // 인자 유무 확인
            if (name == null) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            // AutoCommti() true일때, false 설정
            if (connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }

            Stadium stadium = new Stadium(name);

            int result = stadiumDao.insert(stadium);
            if (result != 1) {
                connection.rollback();
                throw new RuntimeException("실패");
            }
            connection.commit();
            System.out.println("성공");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

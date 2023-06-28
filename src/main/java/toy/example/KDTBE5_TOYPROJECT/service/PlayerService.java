package toy.example.KDTBE5_TOYPROJECT.service;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.model.Player;

import java.sql.Connection;
import java.sql.SQLException;


public class PlayerService {
    private PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void insertPlayer(int teamId, String position, String name) {
        try (Connection connection = DBConnection.getInstance()) {
            if (connection == null) {
                throw new RuntimeException("데이터베이스 연결을 가져올 수 없습니다.");
            }

            // 인자 유무 확인
            if (teamId == 0 | position == null | name == null) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            connection.setAutoCommit(false);

            Player player = new Player(teamId, position, name);

            int result = playerDao.insert(player);
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

package toy.example.KDTBE5_TOYPROJECT.service;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.model.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class PlayerService {
    private PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void insertPlayer(int teamId, String position, String name) {
        try (Connection connection = DBConnection.getInstance()) {
            // null일 경우
            if (connection == null) {
                throw new RuntimeException("연결 실패");
            }

            // 인자 유무 확인
            if (teamId == 0 | position == null | name == null) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            // AutoCommti() true일때, false 설정
            if (connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }

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


    public List<Player> getPlayersByTeam(int teamId){
        return playerDao.getPlayersByTeam(teamId);
    }


}
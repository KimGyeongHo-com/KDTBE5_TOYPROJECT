package toy.example.KDTBE5_TOYPROJECT.service;

import org.springframework.transaction.annotation.Transactional;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.model.Player;


import java.sql.SQLException;


public class PlayerService {
    private PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao){
        this.playerDao = playerDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertPlayer(int teamId, String position, String name) {
        try {
            // 인자 유무 확인
            if (teamId == 0 | position == null | name == null){
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            Player player = new Player(teamId, position, name);

            int result = playerDao.insert(player);
            if (result != 1) {
                throw new RuntimeException();
            }
            System.out.println("성공");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

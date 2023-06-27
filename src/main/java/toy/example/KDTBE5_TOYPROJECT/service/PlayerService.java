package toy.example.KDTBE5_TOYPROJECT.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dto.player.InsertPlayerReqDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;

import java.sql.SQLException;

public class PlayerService {
    private PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao){
        this.playerDao = playerDao;
    }

    @Transactional
    public void insertPlayer(InsertPlayerReqDTO insertPlayerReqDTO) {
        try {
            Player player = insertPlayerReqDTO.toEntity();
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

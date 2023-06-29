package toy.example.KDTBE5_TOYPROJECT.service;


import lombok.RequiredArgsConstructor;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dto.PositionRespDto;
import toy.example.KDTBE5_TOYPROJECT.model.Player;

import java.util.List;


@RequiredArgsConstructor
public class PlayerService {
    private final PlayerDao playerDao;

    public void getPlayerByPosition(){
        playerDao.printPlayersByPosition();
    }

    public List<Player> getPlayersByTeam(int teamId){
        return playerDao.getPlayersByTeam(teamId);
    }


}
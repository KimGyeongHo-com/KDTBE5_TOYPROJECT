package toy.example.KDTBE5_TOYPROJECT.service;

import lombok.RequiredArgsConstructor;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.dto.PositionRespDto;
import toy.example.KDTBE5_TOYPROJECT.dto.TeamRespDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TeamService {

    private final TeamDao teamDao;


    public List<TeamRespDTO> getAllTeam(TeamDao teamDao) {
        return teamDao.getAllTeam();
    }


}

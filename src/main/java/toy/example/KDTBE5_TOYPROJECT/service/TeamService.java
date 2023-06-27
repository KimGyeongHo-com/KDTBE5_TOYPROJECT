package toy.example.KDTBE5_TOYPROJECT.service;

import org.springframework.transaction.annotation.Transactional;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.dto.team.InsertTeamReqDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.SQLException;

public class TeamService {
    private TeamDao teamDao;

    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Transactional
    public void insertTeam(InsertTeamReqDTO insertTeamReqDTO) {
        try {
            Team team = insertTeamReqDTO.toEntity();
            int result = teamDao.insert(team);
            if (result != 1) {
                throw new RuntimeException();
            }
            System.out.println("성공");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

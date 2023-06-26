package toy.example.KDTBE5_TOYPROJECT.service;

import lombok.RequiredArgsConstructor;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.util.List;

@RequiredArgsConstructor
public class TeamService {

    private final TeamDao teamDao;

    public List<Team> getAllTeam() {
        return teamDao.getAllTeam();
    }
}

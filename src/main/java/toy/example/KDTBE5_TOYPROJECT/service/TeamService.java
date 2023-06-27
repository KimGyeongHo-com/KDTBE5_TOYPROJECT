package toy.example.KDTBE5_TOYPROJECT.service;

import org.springframework.transaction.annotation.Transactional;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.dto.UserInputDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.SQLException;

public class TeamService {
    private TeamDao teamDao;

    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Transactional
    public void insertTeam(UserInputDTO userInputDTO) {
        try {
            // 인자 유무 확인
            if (userInputDTO.getArguments() == null){
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            int stadiumId = Integer.parseInt(userInputDTO.getArgument("stadiumId"));
            String name = userInputDTO.getArgument("name");

            Team team = new Team(stadiumId, name);

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

package toy.example.KDTBE5_TOYPROJECT.service;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.Connection;
import java.sql.SQLException;

public class TeamService {
    private TeamDao teamDao;

    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void insertTeam(int stadiumId, String name) {
        try (Connection connection = DBConnection.getInstance()) {
            // null일 경우
            if (connection == null) {
                throw new RuntimeException("연결 실패");
            }

            // 인자 유무 확인
            if (stadiumId == 0 || name == null) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            connection.setAutoCommit(false);

            // 팀 중복 확인
            Team existingTeam = teamDao.findByStadiumId(stadiumId);
            if (existingTeam != null) {
                System.out.println("이미 해당 경기장에 소속된 팀이 있습니다.");
                return;
            }

            Team team = new Team(stadiumId, name);

            int result = teamDao.insert(team);
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

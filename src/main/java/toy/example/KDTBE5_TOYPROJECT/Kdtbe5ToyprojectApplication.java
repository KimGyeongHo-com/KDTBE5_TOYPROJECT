package toy.example.KDTBE5_TOYPROJECT;
import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class Kdtbe5ToyprojectApplication {

	public static void main(String[] args) {


		TeamDao teamDao = new TeamDao();
		List<Team> teamList = teamDao.getAllTeam();

		for(Team team : teamList)
			System.out.println(team);




	}

}

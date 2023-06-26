package toy.example.KDTBE5_TOYPROJECT;

import db.DBConnection;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Team;
import toy.example.KDTBE5_TOYPROJECT.repository.BaseballRepository;
import toy.example.KDTBE5_TOYPROJECT.service.PlayerService;

import java.util.List;

@SpringBootApplication
public class Kdtbe5ToyprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Kdtbe5ToyprojectApplication.class, args);

		DBConnection.getInstance();





		BaseballRepository baseballRepository = new BaseballRepository();
		PlayerService playerService = new PlayerService(BaseballRepository baseballRepository)




		PlayerDao playerDao = PlayerDao.getInstance();
		PlayerService playerService = new PlayerService();
//
//		List<Player> players = playerService.getAllPlayerByPosition("1루수");

//		List<Team> teamList = playerService.getAllTeam();
//
//		for(int i = 0 ; i< teamList.size();i++){
//			System.out.println(teamList.get(i));
//		}
	}

}

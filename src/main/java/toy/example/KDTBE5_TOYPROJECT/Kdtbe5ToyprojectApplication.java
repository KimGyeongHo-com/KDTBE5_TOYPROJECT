package toy.example.KDTBE5_TOYPROJECT;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.dto.UserInputDTO;
import toy.example.KDTBE5_TOYPROJECT.service.PlayerService;
import toy.example.KDTBE5_TOYPROJECT.service.StadiumService;
import toy.example.KDTBE5_TOYPROJECT.service.TeamService;

import java.util.Scanner;

@SpringBootApplication
public class Kdtbe5ToyprojectApplication {
	private static StadiumService stadiumService;
	private static TeamService teamService;
	private static PlayerService playerService;

	public static void main(String[] args) {
		StadiumDao stadiumDao = StadiumDao.getInstance();
		TeamDao teamDao = TeamDao.getInstance();
		PlayerDao playerDao = PlayerDao.getInstance();

		stadiumService = new StadiumService(stadiumDao);
		teamService = new TeamService(teamDao);
		playerService = new PlayerService(playerDao);

		Scanner scanner = new Scanner(System.in);
		System.out.print("어떤 기능을 요청하시겠습니까?");
		String userInput = scanner.nextLine();
		UserInputDTO userInputDTO = new UserInputDTO(userInput);

		switch (userInputDTO.getMenu()){
			case "야구장등록":
				stadiumService.insertStadium(userInputDTO);
				break;
			case "팀등록":
				teamService.insertTeam(userInputDTO);
				break;
			case "선수등록":
				playerService.insertPlayer(userInputDTO);
				break;
			default:
				System.out.println("요청하신 기능을 찾을 수 없습니다.");
				break;
		}
	}
}

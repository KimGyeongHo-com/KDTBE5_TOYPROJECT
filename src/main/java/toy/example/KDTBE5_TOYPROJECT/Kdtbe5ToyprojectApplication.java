package toy.example.KDTBE5_TOYPROJECT;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.dto.player.InsertPlayerReqDTO;
import toy.example.KDTBE5_TOYPROJECT.dto.stadium.InsertStadiumReqDTO;
import toy.example.KDTBE5_TOYPROJECT.dto.team.InsertTeamReqDTO;
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
		String menu = userInput.split("\\?")[0];

		switch (menu){
			case "야구장등록":
				String stadiumName = parseValue(userInput, "name");
				InsertStadiumReqDTO insertStadiumReqDTO = new InsertStadiumReqDTO(stadiumName);
				stadiumService.insertStadium(insertStadiumReqDTO);
				break;
			case "팀등록":
				int stadiumId = Integer.parseInt(parseValue(userInput, "stadiumId"));
				String teamName = parseValue(userInput, "name");
				InsertTeamReqDTO insertTeamReqDTO = new InsertTeamReqDTO(stadiumId, teamName);
				teamService.insertTeam(insertTeamReqDTO);
				break;
			case "선수등록":
				int teamId = Integer.parseInt(parseValue(userInput, "teamId"));
				String playerName = parseValue(userInput, "name");
				String position = parseValue(userInput, "position");
				InsertPlayerReqDTO insertPlayerReqDTO = new InsertPlayerReqDTO(teamId, playerName, position);
				playerService.insertPlayer(insertPlayerReqDTO);
				break;
			default:
				System.out.println("요청된 기능을 찾을 수 없습니다.");
				break;
		}
	}

	private static String parseValue(String userInput, String key) throws IllegalArgumentException {
		String[] keyValuePairs = userInput.split("[?&]");
		for (String pair : keyValuePairs) {
			String[] keyValue = pair.split("=");
			if (keyValue.length == 2 && keyValue[0].equals(key)) {
				return keyValue[1];
			}
		}
		throw new IllegalArgumentException("잘못된 값입니다. : " + key);
	}
}

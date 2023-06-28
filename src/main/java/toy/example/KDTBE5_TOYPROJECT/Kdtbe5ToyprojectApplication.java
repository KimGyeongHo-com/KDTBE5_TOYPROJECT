package toy.example.KDTBE5_TOYPROJECT;

import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.service.PlayerService;
import toy.example.KDTBE5_TOYPROJECT.service.StadiumService;
import toy.example.KDTBE5_TOYPROJECT.service.TeamService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.Connection;

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

        Map<String, String> request = parseUserInput(userInput);
        String menu = request.get("menu");

        switch (menu) {
            case "야구장등록":
                String stadiumName = request.get("name");
                stadiumService.insertStadium(stadiumName);
                break;
            case "팀등록":
                int stadiumId = Integer.parseInt(request.get("stadiumId"));
                String teamName = request.get("name");
                teamService.insertTeam(stadiumId, teamName);
                break;
            case "선수등록":
                int teamId = Integer.parseInt(request.get("teamId"));
                String position = request.get("position");
                String playerName = request.get("name");
                playerService.insertPlayer(teamId, position, playerName);
                break;
            default:
                System.out.println("요청하신 기능을 찾을 수 없습니다.");
                break;
        }
    }

    private static Map<String, String> parseUserInput(String userInput) {
        Map<String, String> request = new HashMap<>();

        if (userInput == null || userInput.isEmpty()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }

        String[] parts = userInput.split("\\?");
        String menu = parts[0];
        request.put("menu", menu);

        if (parts.length > 1) {
            String[] keyValuePairs = parts[1].split("&");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    request.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return request;
    }
}

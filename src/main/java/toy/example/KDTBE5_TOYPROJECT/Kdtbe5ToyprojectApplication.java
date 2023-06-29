package toy.example.KDTBE5_TOYPROJECT;

import toy.example.KDTBE5_TOYPROJECT.dao.OutPlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.dao.TeamDao;
import toy.example.KDTBE5_TOYPROJECT.dto.TeamRespDTO;
import toy.example.KDTBE5_TOYPROJECT.dto.outplayer.OutPlayerRespDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;
import toy.example.KDTBE5_TOYPROJECT.service.OutPlayerService;
import toy.example.KDTBE5_TOYPROJECT.service.PlayerService;
import toy.example.KDTBE5_TOYPROJECT.service.StadiumService;
import toy.example.KDTBE5_TOYPROJECT.service.TeamService;

import java.util.*;

public class Kdtbe5ToyprojectApplication {
    private static StadiumService stadiumService;
    private static TeamService teamService;
    private static PlayerService playerService;

    public static void main(String[] args) {
        StadiumDao stadiumDao = StadiumDao.getInstance();
        TeamDao teamDao = TeamDao.getInstance();
        PlayerDao playerDao = PlayerDao.getInstance();
        OutPlayerService outPlayerService = new OutPlayerService(OutPlayerDao.getInstance());

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
            case "팀목록":
                List<TeamRespDTO> teamRespDTOList = teamService.getAllTeam(teamDao);
                for(TeamRespDTO teamRespDTO : teamRespDTOList)
                    System.out.println(teamRespDTO);
                //findById;
                break;
            case "선수목록":
                int teamId2 = Integer.parseInt(request.get("teamId"));
                List<Player> playerList = new ArrayList<>();
                playerList = playerService.getPlayersByTeam(teamId2);
                for(Player player : playerList)
                        System.out.println(player);
                break;
            case "포지션별목록":
                playerService.getPlayerByPosition();
                break;
            case "경기장목록":
                List<Stadium> stadiumList = stadiumService.getStadiumList();
                stadiumList.stream().forEach(n-> System.out.println(n.getId() + ", " + n.getName() + ", " + n.getCreated_at()));
                break;
            case "퇴출목록":
                List<OutPlayerRespDTO> outPlayerList = outPlayerService.getOutPlayerList();
                outPlayerList.stream().forEach(n -> System.out.println(n.getId() + ", " + n.getName() + ", " + n.getPosition() + ", " + n.getReason() + "," + n.getCreated_at()));
                break;
            case "퇴출등록":
                int playerId = Integer.parseInt(request.get("playerId"));
                String reason = request.get("reason");
                //토String result = playerService.insertOutPlayer(playerId, reason);
                String result = playerService.insertOutPlayer(1, "아아아");
                System.out.println(result);
                break;
            default:
                System.out.println("요청하신 기능을 찾을 수 없습니다.");
                break;
        }
    }

    //경기장목록
//    StadiumService stadiumService = new StadiumService(StadiumDao.getInstance());
//    List<Stadium> stadiumList = stadiumService.getStadiumList();
//    stadiumList.stream().forEach(n-> System.out.println(n.getId() + ", " + n.getName() + ", " + n.getCreateDate()));
//    //퇴출목록
//
//    List<OutPlayerRespDTO> outPlayerList = outPlayerService.getOutPlayerList();
//    outPlayerList.stream().forEach(n -> System.out.println(n.getId() + ", " + n.getName() + ", " + n.getPosition() + ", " + n.getReason() + "," + n.getCreatedAt()));
//    //퇴출선수등록
//    PlayerService playerService = new PlayerService(PlayerDao.getInstance(),OutPlayerDao.getInstance());
//    String result = playerService.insertOutPlayer(1, "퇴출선수");
//System.out.println(result);

    // 문자열 파싱 메서드
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

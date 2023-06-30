package toy.example.KDTBE5_TOYPROJECT.service;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.dao.OutPlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.model.OutPlayer;
import toy.example.KDTBE5_TOYPROJECT.model.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


public class PlayerService {
    private PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void insertPlayer(int teamId, String position, String name) {
        try (Connection connection = DBConnection.getInstance()) {
            // null일 경우
            if (connection == null) {
                throw new RuntimeException("연결 실패");
            }

            // 인자 유무 확인
            if (teamId == 0 | position == null | name == null) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            // AutoCommti() true일때, false 설정
            if (connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }
            List<Player> playerList = playerDao.getPlayersByTeam(teamId);
            int size = playerList.size();
            if (size > 9) {
                throw new IllegalArgumentException("해당 팀의 선수는 가득찼습니다.");
            }

            Player player = new Player(teamId, position, name);

            int result = playerDao.insert(player);
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


    public String insertOutPlayer(int playerId, String reason) {
        OutPlayerDao outPlayerDao = OutPlayerDao.getInstance();

        try (Connection connection = DBConnection.getInstance()) {
            connection.setAutoCommit(false);

            if (playerId < 1 || playerId > 27)
                Logger.getLogger("playerId 입력 오류: " + playerId);

            if (playerDao.findById(playerId) < 1)
                Logger.getLogger(playerId + "번 플레이어 없음");

            OutPlayer outPlayer = OutPlayer.builder()
                    .playerId(playerId)
                    .reason(reason)
                    .build();

            int updateResult = playerDao.updatePlayer(playerId);
            OutPlayer outPlayerCheck = outPlayerDao.getOutPlayerByPlayerId(playerId);
            int insertResult = outPlayerDao.insertOutPlayer(outPlayer);

            if (updateResult < 1 || insertResult < 1 || outPlayerCheck != null) {
                connection.rollback();
                Logger.getLogger("퇴출선수 insert 실패: 롤백");
                return "실패";
            }

            connection.commit();
            connection.setAutoCommit(true); //싱글톤으로 받아도 메모리를 공유하니까 false 했으면 해줘야 되는지
            return "성공";
        }catch (SQLException e) {
            Logger.getLogger("서비스 인서트: " + e.getMessage());
        }
        return "실패";
    }



    public List<Player> getPlayersByTeam(int teamId){
        return playerDao.getPlayersByTeam(teamId);
    }

    public void getPlayerByPosition(){
        playerDao.getPlayerByPosition();
    }

}
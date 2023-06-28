package toy.example.KDTBE5_TOYPROJECT.service;

public class PlayerService {

    private PlayerDao playerDao;
    private OutPlayerDao outPlayerDao;

    public PlayerService(PlayerDao playerDao, OutPlayerDao outPlayerDao){
        this.playerDao = playerDao;
        this.outPlayerDao = outPlayerDao;
    }

    public String insertOutPlayer(int playerId, String reason) {
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
            OutPlayer outPlayerDC = outPlayerDao.getOutPlayerByPlayerId(playerId);
            int insertResult = outPlayerDao.insertOutPlayer(outPlayer);

            if (updateResult < 1 || insertResult < 1 || outPlayerDC != null) {
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


}

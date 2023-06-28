package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.dto.outplayer.OutPlayerRespDTO;
import toy.example.KDTBE5_TOYPROJECT.model.OutPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OutPlayerDao {
    private static OutPlayerDao INSTANCE;

    private Connection connection;

    /* 싱글톤 패턴입니다. */
    private OutPlayerDao(Connection connection){
        this.connection = connection;
    }
    public static synchronized OutPlayerDao getInstance(){
        if(INSTANCE == null)
            INSTANCE = new OutPlayerDao(DBConnection.getInstance());

        return INSTANCE;
    }

    //메소드들 작성하시면 됩니다.
    public int insertOutPlayer(OutPlayer outPlayer) {
        String query = "INSERT INTO out_player(player_id, reason, created_at) VALUES (?, ?, now())";
        int rowCount = -1;

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, outPlayer.getPlayerId());
            statement.setString(2, outPlayer.getReason());
            ///statement.setString(1, "트랜잭션테스트: 잘못된값");

            rowCount = statement.executeUpdate();
        }catch (SQLException e){
            Logger.getLogger("퇴출선수 insert: " + e.getMessage());
        }
        return rowCount;
    }

    public OutPlayer getOutPlayerByPlayerId(int playerId){
        String query = "SELECT * FROM out_player WHERE player_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return OutPlayer.builder()
                        .id(resultSet.getInt("id"))
                        .playerId(resultSet.getInt("player_id"))
                        .reason(resultSet.getString("reason"))
                        .createDate(resultSet.getTimestamp("created_at"))
                        .build();

        } catch (SQLException e){
            Logger.getLogger("퇴출선수 중복 select: " + e.getMessage());
        }
        return null;
    }
}

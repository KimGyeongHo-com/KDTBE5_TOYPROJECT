package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.dto.outplayer.OutPlayerRespDTO;
import toy.example.KDTBE5_TOYPROJECT.model.OutPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OutPlayerDao {
    private Connection connection;

    private static final OutPlayerDao instance = new OutPlayerDao();

    private OutPlayerDao() { connection = DBConnection.getInstance();
    }
    public static OutPlayerDao getInstance() {
        return instance;
    }





    //메소드들 작성하시면 됩니다.
    public int insertOutPlayer(OutPlayer outPlayer) {
        String query = "INSERT INTO outPlayer(playerId, reason, created_at) VALUES (?, ?, now())";
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
        String query = "SELECT * FROM outPlayer WHERE playerId = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return OutPlayer.builder()
                        .id(resultSet.getInt("id"))
                        .playerId(resultSet.getInt("playerId"))
                        .reason(resultSet.getString("reason"))
                        .created_at(resultSet.getTimestamp("created_at"))
                        .build();

        } catch (SQLException e){
            Logger.getLogger("퇴출선수 중복 select: " + e.getMessage());
        }
        return null;
    }

    public List<OutPlayerRespDTO> getOutPlayerList() {
        List<OutPlayerRespDTO> outPlayerRespDTOList = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.position, o.reason, o.created_at FROM player AS p " +
                "LEFT OUTER JOIN outPlayer AS o ON p.id = o.playerId";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                outPlayerRespDTOList.add(OutPlayerRespDTO.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .position(resultSet.getString("position"))
                        .reason(resultSet.getString("reason"))
                        .created_at(resultSet.getTimestamp(5))
                        .build());
            }

        }catch (SQLException e){
            Logger.getLogger("퇴출선수 select: " + e.getMessage());
        }
        return outPlayerRespDTOList;
    }
}

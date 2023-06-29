package toy.example.KDTBE5_TOYPROJECT.dao;


import db.DBConnection;
import lombok.Data;
import toy.example.KDTBE5_TOYPROJECT.dto.PositionRespDto;
import toy.example.KDTBE5_TOYPROJECT.dto.TeamRespDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerDao {

    Connection connection = DBConnection.getInstance();
    private static PlayerDao playerDao = new PlayerDao();

    private PlayerDao() {}

    public static  PlayerDao getInstance(){
        return playerDao;
    }



    public List<Player> getPlayersByTeam(int teamId) {
        List<Player> playerList = new ArrayList<>();
        String sql = "SELECT * FROM player WHERE teamId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, teamId); // 파라미터 위치와 값을 설정

            ResultSet playerSet = statement.executeQuery();
            while (playerSet.next()) {
                Player player = new Player();
                player.setId(playerSet.getInt("id"));
                player.setTeamId(playerSet.getInt("teamId"));
                player.setPosition(playerSet.getString("position"));
                player.setName(playerSet.getString("name"));
                player.setCreateDate(playerSet.getTimestamp("created_date"));


                playerList.add(player);
            }

            playerSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerList;
    }


    public void printPlayersByPosition() {
        String sql = "SELECT " +
                "    position, " +
                "    MAX(CASE WHEN teamId = '1' THEN name END) AS 해테, " +
                "    MAX(CASE WHEN teamId = '2' THEN name END) AS 기아, " +
                "    MAX(CASE WHEN teamId = '3' THEN name END) AS 삼성, " +
                "    MAX(CASE WHEN teamId = '4' THEN name END) AS NC " +
                "FROM Player " +
                "GROUP BY position";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("POSITION | 해테 | 기아 | 삼성 | NC");
            System.out.println("---------------------------------------");

            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String 해테 = resultSet.getString("해테");
                String 기아 = resultSet.getString("기아");
                String 삼성 = resultSet.getString("삼성");
                String NC = resultSet.getString("NC");

                System.out.println(position + " | " + 해테 + " | " + 기아 + " | " + 삼성 + " | " + NC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
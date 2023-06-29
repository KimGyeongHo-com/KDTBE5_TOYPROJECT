package toy.example.KDTBE5_TOYPROJECT.dao;

import java.sql.Connection;

import db.DBConnection;
import lombok.RequiredArgsConstructor;
import toy.example.KDTBE5_TOYPROJECT.dto.TeamRespDTO;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TeamDao {
    private Connection connection;
    private static final TeamDao instance = new TeamDao();

    private TeamDao() {
        connection = DBConnection.getInstance();
    }

    public static TeamDao getInstance() {
        return instance;
    }

    public List<TeamRespDTO> getAllTeam() {
        String sql = "SELECT * FROM team";
        List<TeamRespDTO> teamRespDTOList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet teamSet = stmt.executeQuery();
            while (teamSet.next()) {
                TeamRespDTO teamRespDTO = new TeamRespDTO();
                teamRespDTO.setId(teamSet.getInt("id"));
                teamRespDTO.setStadiumId(teamSet.getInt("stadiumId"));
                teamRespDTO.setName(teamSet.getString("name"));
                teamRespDTO.setCreateDate(teamSet.getTimestamp("created_date"));
                teamRespDTOList.add(teamRespDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teamRespDTOList;
    }

}

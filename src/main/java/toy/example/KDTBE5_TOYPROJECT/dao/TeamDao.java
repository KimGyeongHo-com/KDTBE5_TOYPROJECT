package toy.example.KDTBE5_TOYPROJECT.dao;

import java.sql.Connection;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamDao {
    private Connection connection = DBConnection.getInstance();

    public List<Team> getAllTeam() {
        String sql = "SELECT * FROM team";
        List<Team> teamList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet teamSet = stmt.executeQuery();
            while (teamSet.next()) {
                Team team = new Team();
                team.setId(teamSet.getInt("id"));
                team.setStadiumId(teamSet.getInt("stadiumId"));
                team.setName(teamSet.getString("name"));
                team.setCreateDate(teamSet.getTimestamp("created_date"));
                teamList.add(team);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teamList;
    }

}

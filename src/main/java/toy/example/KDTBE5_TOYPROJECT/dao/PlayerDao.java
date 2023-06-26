package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Team;
import toy.example.KDTBE5_TOYPROJECT.repository.BaseballRepository;

import java.sql.Connection;
import java.util.List;

@Repository
public class PlayerDao {
    private static PlayerDao instance;
    private final BaseballRepository baseballRepository;



    public static synchronized PlayerDao getInstance() {
        if(instance == null)
            instance = new PlayerDao();
        return instance;
    }


    public List<Player> getAllPlayerByPosition(String position) {
        System.out.println("PlayerDaoCalled");
        return baseballRepository.getAllPlayerByPosition(position);
    }

    public List<Team> getAllTeam() {
        return baseballRepository.getAllTeam();
    }

    public List<Player> getAllPlayerByTeam(String name) {
        return baseballRepository.getAllPlayerByTeam(name);
    }
}
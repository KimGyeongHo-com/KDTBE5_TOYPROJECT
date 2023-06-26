package toy.example.KDTBE5_TOYPROJECT.repository;

import org.apache.ibatis.annotations.Mapper;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Stadium;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.util.List;

@Mapper
public interface BaseballRepository {


    List<Player> getAllPlayerByPosition(String position);

    List<Team> getAllTeam();

    List<Player> getAllPlayerByTeam(String name);
}

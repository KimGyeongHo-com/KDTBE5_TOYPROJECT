package toy.example.KDTBE5_TOYPROJECT.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.example.KDTBE5_TOYPROJECT.dao.PlayerDao;
import toy.example.KDTBE5_TOYPROJECT.model.Player;
import toy.example.KDTBE5_TOYPROJECT.model.Team;
import toy.example.KDTBE5_TOYPROJECT.repository.BaseballRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final BaseballRepository baseballRepository;
    PlayerDao playerDao = PlayerDao.getInstance(baseballRepository);


    public List<Player> getAllPlayerByPosition(String position) {
        PlayerDao playerDao = PlayerDao.getInstance(baseballRepository);
        System.out.println("PlayerServiceCalled");
        return playerDao.getAllPlayerByPosition(position);
    }

    public List<Team> getAllTeam() {
        PlayerDao playerDao = PlayerDao.getInstance(baseballRepository);
        System.out.println("gggggggggggggggggggggggg");
        return playerDao.getAllTeam();
    }

    public List<Player> getAllPlayerByTeam(String name) {
        PlayerDao playerDao = PlayerDao.getInstance(baseballRepository);
        return playerDao.getAllPlayerByTeam(name);
    }
}
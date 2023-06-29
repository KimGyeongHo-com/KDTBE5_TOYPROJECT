package toy.example.KDTBE5_TOYPROJECT.service;

import toy.example.KDTBE5_TOYPROJECT.dao.OutPlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dto.outplayer.OutPlayerRespDTO;

import java.util.List;

public class OutPlayerService {
    private OutPlayerDao expulsionDao;

    public OutPlayerService(OutPlayerDao expulsionDao){
        this.expulsionDao = expulsionDao;
    }

    public List<OutPlayerRespDTO> getOutPlayerList() {
        return expulsionDao.getOutPlayerList();
    }

}

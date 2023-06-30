package toy.example.KDTBE5_TOYPROJECT.service;

import toy.example.KDTBE5_TOYPROJECT.dao.OutPlayerDao;
import toy.example.KDTBE5_TOYPROJECT.dto.outplayer.OutPlayerRespDTO;

import java.util.List;

public class OutPlayerService {
    private OutPlayerDao outPlayerDao;

    public OutPlayerService(OutPlayerDao expulsionDao){
        this.outPlayerDao = expulsionDao;
    }

    public List<OutPlayerRespDTO> getOutPlayerList() {
        return outPlayerDao.getOutPlayerList();
    }

}

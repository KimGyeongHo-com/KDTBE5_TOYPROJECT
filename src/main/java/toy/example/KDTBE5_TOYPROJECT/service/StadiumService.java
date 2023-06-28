package toy.example.KDTBE5_TOYPROJECT.service;

public class StadiumService {

    private StadiumDao stadiumDao;

    public StadiumService(StadiumDao stadiumDao){
        this.stadiumDao = stadiumDao;
    }

    public List<Stadium> getStadiumList() {
        return stadiumDao.getStadiumList();
    }
}

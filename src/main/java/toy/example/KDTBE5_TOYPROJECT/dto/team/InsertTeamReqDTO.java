package toy.example.KDTBE5_TOYPROJECT.dto.team;

import toy.example.KDTBE5_TOYPROJECT.model.Team;

public class InsertTeamReqDTO {
    private int stadiumId;
    private String name;

    public InsertTeamReqDTO(int stadiumId, String name) {
        this.stadiumId = stadiumId;
        this.name = name;
    }

    public Team toEntity() {
        return Team.builder()
                .stadiumId(stadiumId)
                .name(name)
                .build();
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public String getName() {
        return name;
    }
}

package toy.example.KDTBE5_TOYPROJECT.dto.player;

import toy.example.KDTBE5_TOYPROJECT.model.Player;

public class InsertPlayerReqDTO {
    private	int teamId;
    private	String position;
    private	String name;

    public InsertPlayerReqDTO(int teamId, String position, String name) {
        this.teamId = teamId;
        this.position = position;
        this.name = name;
    }

    public Player toEntity(){
        return Player.builder()
                .teamId(teamId)
                .position(position)
                .name(name)
                .build();
    }
}

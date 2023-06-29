package toy.example.KDTBE5_TOYPROJECT.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
public class Player {
    private	int id;
    private	int teamId;
    private	String position;
    private	String name;
    private	Timestamp created_at;

    public Player(int teamId, String position, String name) {
        this.teamId = teamId;
        this.position = position;
        this.name = name;
    }
}
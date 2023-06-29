package toy.example.KDTBE5_TOYPROJECT.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Team {
    private int id;
    private int stadiumId;
    private String name;
    private Timestamp create_at;

    public Team(int stadiumId, String name) {
        this.stadiumId = stadiumId;
        this.name = name;
    }
}
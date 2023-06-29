package toy.example.KDTBE5_TOYPROJECT.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
public class Team {
    private int id;
    private int stadiumId;
    private String name;
    private Timestamp createDate;

    public Team(int stadiumId, String name) {
        this.stadiumId = stadiumId;
        this.name = name;
    }
}
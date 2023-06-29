package toy.example.KDTBE5_TOYPROJECT.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class TeamRespDTO {
    private int id;
    private int stadiumId;
    private String name;
    private Timestamp create_at;



}

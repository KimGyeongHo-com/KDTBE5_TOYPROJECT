package toy.example.KDTBE5_TOYPROJECT.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    private int id;
    private int stadiumId;
    private String name;
    private Timestamp createDate;
}
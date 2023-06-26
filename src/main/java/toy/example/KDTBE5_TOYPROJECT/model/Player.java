package toy.example.KDTBE5_TOYPROJECT.model;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class Player {
    private	int id;
    private	int teamId;
    private	String position;
    private	String name;
    private	Timestamp createDate;
}
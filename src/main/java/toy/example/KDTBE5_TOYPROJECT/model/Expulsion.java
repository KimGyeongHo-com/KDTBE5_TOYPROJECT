package toy.example.KDTBE5_TOYPROJECT.model;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Expulsion {
    private	int id;
    private	int playerId;
    private	String reason;
    private Timestamp createDate;
}

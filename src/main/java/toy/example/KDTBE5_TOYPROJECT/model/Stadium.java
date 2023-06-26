package toy.example.KDTBE5_TOYPROJECT.model;

import lombok.Data;

import java.sql.Timestamp;



@Data
public class Stadium {
    private	int id;
    private	String name;
    private	Timestamp createDate;
}
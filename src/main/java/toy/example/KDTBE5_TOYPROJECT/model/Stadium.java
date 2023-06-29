package toy.example.KDTBE5_TOYPROJECT.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stadium {
    private	int id;
    private	String name;
    private	Timestamp createDate;

    public Stadium(String name) {
        this.name = name;
    }
}
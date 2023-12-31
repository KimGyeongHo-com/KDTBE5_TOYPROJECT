package toy.example.KDTBE5_TOYPROJECT.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;



@Data
@Builder
@AllArgsConstructor
public class Stadium {
    private	int id;
    private	String name;
    private	Timestamp created_at;

    public Stadium(String name) {
        this.name = name;
    }
}
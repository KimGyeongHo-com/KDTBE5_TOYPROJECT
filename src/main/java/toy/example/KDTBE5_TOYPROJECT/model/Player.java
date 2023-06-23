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
public class Player {
    private	int id;
    private	int teamId;
    private	String position;
    private	String name;
    private	Timestamp createDate;
}
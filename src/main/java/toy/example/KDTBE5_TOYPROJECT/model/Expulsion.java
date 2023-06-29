package toy.example.KDTBE5_TOYPROJECT.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class Expulsion {
    private	int id;
    private	int playerId;
    private	String reason;
    private Timestamp created_at;
}

package toy.example.KDTBE5_TOYPROJECT.dto.outplayer;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;


@Getter
@Builder
public class OutPlayerRespDTO {
    private int id;
    private String name;
    private String position;
    private String reason;
    private Timestamp created_at;
}

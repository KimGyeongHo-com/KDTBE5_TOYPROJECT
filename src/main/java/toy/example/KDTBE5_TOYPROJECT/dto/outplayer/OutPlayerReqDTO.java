package toy.example.KDTBE5_TOYPROJECT.dto.outplayer;

import lombok.Builder;
import lombok.Getter;
import toy.example.KDTBE5_TOYPROJECT.model.OutPlayer;


@Getter
@Builder
public class OutPlayerReqDTO {
    private int playerId;
    private EReason reason;

    public enum EReason{
        도박("도박"),
        질병("질병"),
        기량저하("기량저하"),
        은퇴("은퇴");

        private String reason;
        EReason(String reason){
            this.reason = reason;
        }
    }
}

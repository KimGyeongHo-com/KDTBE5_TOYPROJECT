package toy.example.KDTBE5_TOYPROJECT.dto.stadium;

import toy.example.KDTBE5_TOYPROJECT.model.Stadium;

public class InsertStadiumReqDTO {
    private	String name;

    public InsertStadiumReqDTO(String name) {
        this.name = name;
    }

    public Stadium toEntity(){
        return Stadium.builder()
                .name(name)
                .build();
    }

    public String getName() {
        return name;
    }
}

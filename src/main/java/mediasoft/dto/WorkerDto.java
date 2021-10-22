package mediasoft.dto;

import lombok.Getter;

@Getter
public class WorkerDto {

    private final Integer id;

    private final String fam;
    private final String im;
    private final String otch;
    private final String email;

    public WorkerDto(Integer id,
                     String fam,
                     String im,
                     String otch,
                     String email) {
        this.id = id;
        this.fam = fam;
        this.im = im;
        this.otch = otch;
        this.email = email;
    }
}
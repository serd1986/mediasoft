package mediasoft.dto;

import lombok.Getter;

@Getter
public class WorkerDto {

    private final Integer id;

    private final String fam;
    private final String im;
    private final String otch;
    private final String text;

    public WorkerDto(Integer id,
                    String fam,
                    String im,
                    String otch,
                    String text) {
        this.id = id;
        this.fam = fam;
        this.im = im;
        this.otch = otch;
        this.text = text;
    }
}
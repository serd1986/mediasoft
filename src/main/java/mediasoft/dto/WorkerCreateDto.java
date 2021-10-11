package mediasoft.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WorkerCreateDto {

    private final String fam;
    private final String im;
    private final String otch;

    public WorkerCreateDto(@JsonProperty("fam") String fam,
                           @JsonProperty("im") String im,
                           @JsonProperty("otch") String otch) {
        this.fam = fam;
        this.im = im;
        this.otch = otch;
    }
}
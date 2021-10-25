package mediasoft.dto.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WorkerCreateDto {

    private final String fam;
    private final String im;
    private final String otch;
    private final String email;

    public WorkerCreateDto(@JsonProperty("fam") String fam,
                           @JsonProperty("im") String im,
                           @JsonProperty("otch") String otch,
                           @JsonProperty("email") String email) {
        this.fam = fam;
        this.im = im;
        this.otch = otch;
        this.email = email;
    }
}
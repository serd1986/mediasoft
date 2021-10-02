package mediasoft.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WorkerEditDto {

    private final String fam;
    private final String im;
    private final String otch;
    private final String text;
    public WorkerEditDto(@JsonProperty("fam") String fam,
                         @JsonProperty("im") String im,
                         @JsonProperty("otch") String otch,
                         @JsonProperty("text") String text) {
        this.fam = fam;
        this.im = im;
        this.otch = otch;
        this.text = text;
    }
}
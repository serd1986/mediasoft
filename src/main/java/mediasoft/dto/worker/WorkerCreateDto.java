package mediasoft.dto.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mediasoft.entity.Role;

import java.util.Set;

@Getter
public class WorkerCreateDto {

    private final String fam;
    private final String im;
    private final String otch;
    private final String email;
    private final String password;

    public WorkerCreateDto(@JsonProperty("fam") String fam,
                           @JsonProperty("im") String im,
                           @JsonProperty("otch") String otch,
                           @JsonProperty("email") String email,
                           @JsonProperty("password") String password) {
        this.fam = fam;
        this.im = im;
        this.otch = otch;
        this.email = email;
        this.password = password;
    }
}
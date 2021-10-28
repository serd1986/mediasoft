package mediasoft.dto.authentication;

import lombok.Getter;

import java.util.Set;

@Getter
public class WorkerAuthenticationInfoDto {
    private final Integer id;
    private final String email;
    private final String password;
    private final Set<String> roleCodes;

    public WorkerAuthenticationInfoDto(Integer id,
                                     String email,
                                     String password,
                                     Set<String> roleCodes) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleCodes = roleCodes;
    }
}
package mediasoft.dto.worker;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WorkerWithRolesDto {

    @EqualsAndHashCode.Include
    private final Integer id;

    private final String email;

    private final List<String> codes;

    public WorkerWithRolesDto(Integer id,
                            String email,
                            List<String> codes) {
        this.id = id;
        this.email = email;
        this.codes = codes;
    }
}
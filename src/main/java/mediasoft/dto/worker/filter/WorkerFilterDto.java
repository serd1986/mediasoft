package mediasoft.dto.worker.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mediasoft.enums.WorkerField;

import java.util.Collection;

@Getter
public class WorkerFilterDto {

    private final WorkerField workerField;

    private final Collection<String> values;

    public WorkerFilterDto(@JsonProperty("workerField") WorkerField workerField,
                           @JsonProperty("values") Collection<String> values) {
        this.workerField = workerField;
        this.values = values;
    }
}

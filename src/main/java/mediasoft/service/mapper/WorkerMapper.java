package mediasoft.service.mapper;

import mediasoft.dto.WorkerDto;
import mediasoft.entity.Worker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkerMapper {

    public WorkerDto mapWorkerToWorkerDto(Worker model) {
        return new WorkerDto(
                model.getId(),
                model.getFam(),
                model.getIm(),
                model.getOtch()
        );
    }

    public List<WorkerDto> mapWorkerToWorkerDto(Collection<Worker> model) {
        return model.stream()
                .map(this::mapWorkerToWorkerDto)
                .collect(Collectors.toList());
    }
}
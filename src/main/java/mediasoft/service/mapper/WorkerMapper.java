package mediasoft.service.mapper;

import mediasoft.dto.worker.WorkerDto;
import mediasoft.dto.worker.WorkerWithRolesDto;
import mediasoft.entity.Role;
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
                model.getOtch(),
                model.getEmail());
    }

    public List<WorkerDto> mapWorkerToWorkerDto(Collection<Worker> model) {
        return model.stream()
                .map(this::mapWorkerToWorkerDto)
                .collect(Collectors.toList());
    }

    public WorkerWithRolesDto mapWorkerToWorkerWithRolesDto(Worker worker) {
        return new WorkerWithRolesDto(
                worker.getId(),
                worker.getEmail(),
                worker.getRoles().stream().map(Role::getCode).collect(Collectors.toList())
        );
    }

    public List<WorkerWithRolesDto> mapWorkerToWorkerWithRolesDto(Collection<Worker> workers) {
        return workers.stream()
                .map(this::mapWorkerToWorkerWithRolesDto)
                .distinct()
                .collect(Collectors.toList());
    }
}
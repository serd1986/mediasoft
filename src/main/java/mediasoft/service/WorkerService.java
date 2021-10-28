package mediasoft.service;

import mediasoft.dto.authentication.WorkerAuthenticationInfoDto;
import mediasoft.dto.worker.WorkerCreateDto;
import mediasoft.dto.worker.WorkerDto;
import mediasoft.dto.worker.WorkerEditDto;
import mediasoft.dto.worker.WorkerWithRolesDto;
import mediasoft.dto.worker.filter.WorkerFilterDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface WorkerService {

    WorkerDto findByIm(String im);

    List<WorkerDto> getAllWorkerDtos();

    WorkerDto editWorkerDto(Integer noteId, WorkerEditDto noteEditDto);

    List<WorkerWithRolesDto> getWorkers();

    List<WorkerWithRolesDto> getWorkers(Collection<WorkerFilterDto> filters);

    Optional<WorkerAuthenticationInfoDto> findAuthenticationInfo(String email);

    Integer getId(String email);

    void editRole(Integer workerId, Collection<String> roleCodes);
}
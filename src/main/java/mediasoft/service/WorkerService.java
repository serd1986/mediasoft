package mediasoft.service;

import mediasoft.dto.WorkerCreateDto;
import mediasoft.dto.WorkerDto;
import mediasoft.dto.WorkerEditDto;

import java.util.List;

public interface WorkerService {

    List<WorkerDto> getAllWorkerDtos();

    WorkerDto createWorkerDto(WorkerCreateDto noteCreateDto);

    WorkerDto editWorkerDto(Integer noteId, WorkerEditDto noteEditDto);
}
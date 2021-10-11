package mediasoft.controller;

import mediasoft.dto.WorkerCreateDto;
import mediasoft.dto.WorkerDto;
import mediasoft.dto.WorkerEditDto;
import mediasoft.service.WorkerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public List<WorkerDto> getAllWorkers() {
        return workerService.getAllWorkerDtos();
    }

    @PostMapping
    public WorkerDto createWorker(@RequestBody WorkerCreateDto workerCreateDto) {
        return workerService.createWorkerDto(workerCreateDto);
    }

    @PutMapping("/{id}")
    public WorkerDto editWorker(@RequestBody WorkerEditDto WorkerEditDto,
                                @PathVariable("id") Integer WorkerId) {
        return workerService.editWorkerDto(WorkerId, WorkerEditDto);
    }
}
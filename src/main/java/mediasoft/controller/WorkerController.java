package mediasoft.controller;

import mediasoft.dto.WorkerCreateDto;
import mediasoft.dto.WorkerDto;
import mediasoft.dto.WorkerEditDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @GetMapping
    public List<WorkerDto> getAllWorker() {
        return List.of(
                new WorkerDto(1, "Ivanov", "Petr", "Sergeevih", "Test_1"),
                new WorkerDto(2, "Smirnov", "Aleksey", "Viktorovih", "Test_2"),
                new WorkerDto(3, "Kozlov", "Mihail", "Vladimirovih", "Test_3")
        );
    }

    @PostMapping
    public WorkerDto createWorker(@RequestBody WorkerCreateDto workerCreateDto) {

        WorkerDto workerDto = new WorkerDto(
                10,
                workerCreateDto.getFam(),
                workerCreateDto.getIm(),
                workerCreateDto.getOtch(),
                workerCreateDto.getText()
        );

        return workerDto;
    }

    @PutMapping("/v1/test/{id}/edit")
    public WorkerDto editWorker(@RequestBody WorkerEditDto workerEditDto,
                                @PathVariable("id") Integer workerId) {

        WorkerDto noteDto = new WorkerDto(
                workerId,
                workerEditDto.getFam(),
                workerEditDto.getIm(),
                workerEditDto.getOtch(),
                workerEditDto.getText()
        );

        return noteDto;
    }
}
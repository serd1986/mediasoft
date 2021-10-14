package mediasoft.service.impl;

import mediasoft.annotation.Loggable;
import mediasoft.dto.WorkerCreateDto;
import mediasoft.dto.WorkerDto;
import mediasoft.dto.WorkerEditDto;
import mediasoft.entity.Worker;
import mediasoft.repository.WorkerRepository;
import mediasoft.service.WorkerService;
import mediasoft.service.factory.WorkerFactory;
import mediasoft.service.mapper.WorkerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaWorkerService implements WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;
    private final WorkerFactory workerFactory;

    public JpaWorkerService(WorkerRepository workerRepository,
                            WorkerMapper WorkerMapper,
                            WorkerFactory WorkerFactory) {
        this.workerRepository = workerRepository;
        this.workerMapper = WorkerMapper;
        this.workerFactory = WorkerFactory;
    }


    @Override
    public List<WorkerDto> getAllWorkerDtos() {
        List<Worker> Workers = workerRepository.findAll();
        return workerMapper.mapWorkerToWorkerDto(Workers);
    }

    @Loggable
    @Override
    public WorkerDto createWorkerDto(WorkerCreateDto workerCreateDto) {
        Worker Worker = workerFactory.build(
                workerCreateDto.getFam(),
                workerCreateDto.getIm(),
                workerCreateDto.getOtch()

        );

        Worker = workerRepository.saveAndFlush(Worker);

        return workerMapper.mapWorkerToWorkerDto(Worker);
    }

    @Loggable
    @Override
    public WorkerDto editWorkerDto(Integer WorkerId, WorkerEditDto WorkerEditDto) {
        Worker worker = workerRepository.findById(WorkerId).orElseThrow();

        worker.setFam(WorkerEditDto.getFam());
        worker.setIm(WorkerEditDto.getFam());
        worker.setOtch(WorkerEditDto.getFam());
        workerRepository.saveAndFlush(worker);

        return workerMapper.mapWorkerToWorkerDto(worker);
    }
}
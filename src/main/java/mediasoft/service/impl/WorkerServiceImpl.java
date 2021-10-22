package mediasoft.service.impl;

import mediasoft.annotation.Loggable;
import mediasoft.dto.WorkerCreateDto;
import mediasoft.dto.WorkerDto;
import mediasoft.dto.WorkerEditDto;
import mediasoft.entity.Worker;
import mediasoft.exception.ConflictException;
import mediasoft.repository.WorkerRepository;
import mediasoft.service.WorkerService;
import mediasoft.service.factory.WorkerFactory;
import mediasoft.service.mapper.WorkerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;
    private final WorkerFactory workerFactory;

    public WorkerServiceImpl(WorkerRepository workerRepository,
                             WorkerMapper WorkerMapper,
                             WorkerFactory WorkerFactory) {
        this.workerRepository = workerRepository;
        this.workerMapper = WorkerMapper;
        this.workerFactory = WorkerFactory;
    }

    @Loggable
    @Override
    public WorkerDto findByIm(String im) {
        Worker worker = workerRepository.findByIm(im).orElseThrow(() -> {
            return new ConflictException("Worker not found");
        });
        return workerMapper.mapWorkerToWorkerDto(worker);
    }

    @Override
    public List<WorkerDto> getAllWorkerDtos() {
        List<Worker> workers = workerRepository.findAll();
        return workerMapper.mapWorkerToWorkerDto(workers);
    }

    @Loggable
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
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
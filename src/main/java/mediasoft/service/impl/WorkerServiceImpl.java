package mediasoft.service.impl;

import mediasoft.annotation.Loggable;
import mediasoft.dto.worker.WorkerCreateDto;
import mediasoft.dto.worker.WorkerDto;
import mediasoft.dto.worker.WorkerEditDto;
import mediasoft.dto.worker.WorkerWithRolesDto;
import mediasoft.dto.worker.filter.WorkerFilterDto;
import mediasoft.entity.Worker;
import mediasoft.exception.ConflictException;
import mediasoft.repository.WorkerRepository;
import mediasoft.repository.specification.WorkerSpecification;
import mediasoft.service.WorkerService;
import mediasoft.service.factory.WorkerFactory;
import mediasoft.service.mapper.WorkerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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
                workerCreateDto.getOtch(),
                workerCreateDto.getEmail()

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

    @Override
    public List<WorkerWithRolesDto> getWorkers() {
        List<Worker> workers = workerRepository.findAllWithRoles();
        return workerMapper.mapWorkerToWorkerWithRolesDto(workers);
    }

    @Override
    public List<WorkerWithRolesDto> getWorkers(Collection<WorkerFilterDto> filters) {
        List<Worker> workers = workerRepository.findAll(WorkerSpecification.findWorkers(filters));
        return workerMapper.mapWorkerToWorkerWithRolesDto(workers);
    }
}
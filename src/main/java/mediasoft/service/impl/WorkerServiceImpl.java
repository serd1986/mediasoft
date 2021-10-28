package mediasoft.service.impl;

import mediasoft.annotation.Loggable;
import mediasoft.dto.authentication.WorkerAuthenticationInfoDto;
import mediasoft.dto.worker.WorkerCreateDto;
import mediasoft.dto.worker.WorkerDto;
import mediasoft.dto.worker.WorkerEditDto;
import mediasoft.dto.worker.WorkerWithRolesDto;
import mediasoft.dto.worker.filter.WorkerFilterDto;
import mediasoft.entity.Role;
import mediasoft.entity.Worker;
import mediasoft.exception.ConflictException;
import mediasoft.exception.UserAlreadyExistsException;
import mediasoft.repository.RoleRepository;
import mediasoft.repository.WorkerRepository;
import mediasoft.repository.specification.WorkerSpecification;
import mediasoft.service.WorkerService;
import mediasoft.service.factory.WorkerFactory;
import mediasoft.service.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;
    private final RoleRepository roleRepository;
    private final WorkerFactory workerFactory;

    public WorkerServiceImpl(WorkerRepository workerRepository,
                             WorkerMapper WorkerMapper,
                             RoleRepository roleRepository,
                             WorkerFactory workerFactory) {
        this.workerRepository = workerRepository;
        this.workerMapper = WorkerMapper;
        this.roleRepository = roleRepository;
        this.workerFactory=workerFactory;
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

    @Override
    public Optional<WorkerAuthenticationInfoDto> findAuthenticationInfo(String email) {
        Optional<Worker> workerOpt = workerRepository.findOneWithRolesByEmail(email);

        if (workerOpt.isPresent()) {
            Worker worker = workerOpt.get();
            return Optional.of(new WorkerAuthenticationInfoDto(
                    worker.getId(),
                    worker.getEmail(),
                    worker.getPassword(),
                    worker.getRoles().stream().map(Role::getCode).collect(Collectors.toSet())
            ));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Integer getId(String email) {
        return workerRepository.findOneByEmail(email).getId();
    }

    @Loggable
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public void editRole(Integer userId, Collection<String> roleCodes) {
        Worker worker = workerRepository.findById(userId).orElseThrow();
        Set<Role> newRoles = roleRepository.findAllByCodeIn(roleCodes);
        worker.setRoles(newRoles);
        workerRepository.save(worker);
    }
}
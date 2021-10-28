package mediasoft.service.authentication;

import mediasoft.dto.authentication.WorkerAuthenticationInfoDto;
import mediasoft.dto.worker.WorkerCreateDto;
import mediasoft.dto.worker.WorkerDto;
import mediasoft.entity.Worker;
import mediasoft.exception.UserAlreadyExistsException;
import mediasoft.repository.RoleRepository;
import mediasoft.repository.WorkerRepository;
import mediasoft.service.WorkerService;
import mediasoft.service.factory.WorkerFactory;
import mediasoft.service.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Optional;

@Service
public class JpaWorkerDetailsService implements UserDetailsService {

    private static final String EX_MSG_TMPL_USER_NOT_FOUND = "[email = %s] Пользователь не найден";

    private final WorkerService workerService;
    private final WorkerFactory workerFactory;
    private final RoleRepository roleRepository;
    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;

    public JpaWorkerDetailsService(WorkerService workerService, WorkerFactory workerFactory, RoleRepository roleRepository, WorkerRepository workerRepository, WorkerMapper workerMapper) {
        this.workerService = workerService;
        this.workerFactory = workerFactory;
        this.roleRepository = roleRepository;
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userInfo = workerService.findAuthenticationInfo(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(EX_MSG_TMPL_USER_NOT_FOUND, email)));

        return User.builder()
                .username(userInfo.getEmail())
                .password(userInfo.getPassword())
                .roles(userInfo.getRoleCodes().toArray(new String[0]))
                .build();
    }

    public WorkerDto saveWorker(WorkerCreateDto workerCreateDto) {
        Assert.notNull(workerCreateDto, "Worker object be null");
        Optional<WorkerAuthenticationInfoDto> workerOpt = workerService.findAuthenticationInfo(workerCreateDto.getEmail());

        if (workerOpt.isPresent()) {
                new UsernameNotFoundException(String.format(EX_MSG_TMPL_USER_NOT_FOUND, workerCreateDto.getEmail()));
        }
        Worker  worker = workerFactory.build(
                workerCreateDto.getFam(),
                workerCreateDto.getIm(),
                workerCreateDto.getOtch(),
                workerCreateDto.getEmail(),
                bCryptPasswordEncoder.encode(workerCreateDto.getPassword()),
                roleRepository.findAllByCodeIn(Collections.singleton("user"))

        );

        worker = workerRepository.saveAndFlush(worker);
        return workerMapper.mapWorkerToWorkerDto(worker);
    }


}
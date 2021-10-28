package mediasoft.service.factory;


import mediasoft.entity.Role;
import mediasoft.entity.Worker;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class WorkerFactory {

    public Worker build(String fam, String im, String otch,String email, String password, Set<Role> roles) {
        return new Worker(fam, im, otch, email, password, roles);
    }
}
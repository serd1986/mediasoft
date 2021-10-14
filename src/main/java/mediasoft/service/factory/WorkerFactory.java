package mediasoft.service.factory;

import mediasoft.entity.Worker;
import org.springframework.stereotype.Component;

    @Component
    public class WorkerFactory {

        public Worker build(String fam, String im, String otch) {
            return new Worker(fam, im, otch);
        }
    }
package mediasoft.service.context.impl;

import lombok.Getter;
import lombok.Setter;
import mediasoft.service.context.WorkerContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class WorkerContextImpl implements WorkerContext {

    @Getter
    @Setter
    private String email = null;
}

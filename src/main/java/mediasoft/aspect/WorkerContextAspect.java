package mediasoft.aspect;

import mediasoft.service.context.WorkerContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
@Order(1)
public class WorkerContextAspect {

    private static final String HEADER_NAME_EMAIL = "email";

    private final WorkerContext workerContext;

    public WorkerContextAspect(WorkerContext workerContext) {
        this.workerContext = workerContext;
    }

    @Before("execution(public * *(..)) " +
            "&& within(@org.springframework.web.bind.annotation.RestController *)")
    public void setUserContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        workerContext.setEmail(email);
    }
}
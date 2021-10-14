package mediasoft.aspect;

import lombok.extern.slf4j.Slf4j;
import mediasoft.annotation.Loggable;
import mediasoft.service.context.UserContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Order(2)
public class LoggingAspect {

    private final UserContext userContext;

    public LoggingAspect(UserContext userContext) {
        this.userContext = userContext;
    }


    @After("@annotation(loggable)")
    public void loggable(JoinPoint joinPoint, Loggable loggable) {
        String email = userContext.getEmail();
        System.out.printf(
                "[email = %s] Hello, execute: %s",
                email, joinPoint.getSignature().getName()
        );
    }

    @Before("@annotation(loggable)")
    public void logCall(JoinPoint joinPoint, Loggable loggable){
        StringBuilder message = new StringBuilder("Method:");
        message.append(joinPoint.getSignature().getName()).append("!");
      Arrays.stream(joinPoint.getArgs()).forEach(arg->
             message.append("arg: ").append(arg).append("!"));
        System.out.print(message.toString());
      log.info(message.toString());
    }

}
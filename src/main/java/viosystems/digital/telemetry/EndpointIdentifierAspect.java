package viosystems.digital.telemetry;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.logging.MDC;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Slf4j
@Order(value = 5)
@Aspect
public class EndpointIdentifierAspect {

  @Around("@annotation(viosystems.digital.telemetry.EndpointIdentifier)")
  public Object getEndpointIdentifier(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

    Method method = methodSignature.getMethod();

    EndpointIdentifier endpointIdentifier = method.getAnnotation(EndpointIdentifier.class);
    MDC.put("endpoint_identifier", endpointIdentifier.id());

    try {
      return proceedingJoinPoint.proceed();
    } finally {
      MDC.remove("endpoint_identifier");
    }

  }
}

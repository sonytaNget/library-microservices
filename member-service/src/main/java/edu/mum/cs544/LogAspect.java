package edu.mum.cs544;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
public class LogAspect {

    // Aspect
    @Before("execution(* edu.mum.cs544.controller.MemberRestController.*(..))") // Pointcut
    public void logBeforeMember(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        System.out.println("Before Log Member: " + signature.getName());  // Advice
    }
}

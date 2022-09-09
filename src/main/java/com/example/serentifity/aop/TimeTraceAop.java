package com.example.serentifity.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //이렇게 써주면서 Bean으로 만들어줘도 되지만 특별하니까 SpringConfig에 직접 등록해주자
public class TimeTraceAop {

    @Around("execution(* com.example.serentifity..*(..))") //우리 패키지 안에있는것들은 다 적용하라는 말
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : "+joinPoint.toString());
        try{
            return joinPoint.proceed(); //위에처럼 쓰기보다는 이렇게 한줄에 쓸 수 있다
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : "+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}

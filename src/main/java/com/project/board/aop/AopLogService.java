package com.project.board.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

// Aspect를 통해 AOP 적용
@Aspect
// Component를 통해 spring bean을 만드는 것
@Component
@Slf4j
public class AopLogService {

    // 에러가 났을 때, 사용자가 어떤 url로 호출했는지 어떤 parameter로 넘겼는지
    // 이런 정보를 log로 남기고 싶다면

    @Around("execution(* com.project.board.author.controller..*.*(..))")
    public Object controllerLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        JSONObject jsonObject = new JSONObject();
        
        // 사용자의 request 정보 Servlet을 통해 가져옴
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        jsonObject.put("method name", proceedingJoinPoint.getSignature().getName());
        jsonObject.put("CRUD name", req.getMethod());
        log.info("request 정보 " + jsonObject);

        JSONObject jsonObject2 = new JSONObject();
        while(req.getParameterNames().hasMoreElements()){
            String body1 = req.getParameterNames().nextElement();
            String body2 = req.getParameterNames().nextElement().replaceAll("\\.", "-");
            jsonObject2.put(body2, req.getParameter(body1));
        }
        log.info("request parameters" + jsonObject2);

        return proceedingJoinPoint.proceed();
    }
}
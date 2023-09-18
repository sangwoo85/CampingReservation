package com.campingreservationbackend.aop;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ContollerLogginAop {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContollerLogginAop.class);

    @Around("execution(* com.campingreservationbackend.web.*.*(..))")
    public Object controllerLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method_name = proceedingJoinPoint.getSignature().getName();
        LOGGER.info(method_name +"====================== START");

        Map<String, Object> paramMap = param(proceedingJoinPoint);
        Map<String,Object> inParam = (Map<String,Object>)paramMap.get("inParam");
        LOGGER.info(method_name + "====================== Param : " + new JsonMapper().writeValueAsString(inParam));

        Object responseObj = proceedingJoinPoint.proceed();

        LOGGER.info(method_name +"====================== END");
        LOGGER.info(method_name +"====================== END Response : "+responseObj.toString());

        return responseObj;
    }

    /**
     * @title Parameter Parsing
     * */
    private Map<String,Object> param(JoinPoint joinPoint){
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] paramName = codeSignature.getParameterNames();
        Object[] args =  joinPoint.getArgs();
        Map<String,Object> param = new HashMap<String,Object>();

        for(int i = 0; i < paramName.length; i++){
            param.put(paramName[i],args[i]);
        }

        return param;
    }



}

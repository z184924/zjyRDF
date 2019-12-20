package cn.zhangjingyao.zjyrdf.aspect;

import cn.zhangjingyao.zjyrdf.entity.log.SystemLog;
import cn.zhangjingyao.zjyrdf.entity.system.User;
import cn.zhangjingyao.zjyrdf.mq.ProducerService;
import cn.zhangjingyao.zjyrdf.security.service.CustomTokenServices;
import cn.zhangjingyao.zjyrdf.util.IpUtil;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author
 */
@Component
@Aspect
public class SystemLogAspect {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    CustomTokenServices customTokenServices;
    @Autowired
    ProducerService producerService;

    @Around("execution(* cn.zhangjingyao.zjyrdf.controller..*.*(..)) && !@annotation(cn.zhangjingyao.zjyrdf.annotation.SystemLog))")
    public Object writeSimpleLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AccessToken accessToken = customTokenServices.getAccessToken(authentication);
        User user = (User) accessToken.getAdditionalInformation().get("user");
        logger.info(user.getAccount() + ":" + request.getServletPath() + " - " + JSON.toJSON(request.getParameterMap()));
        Object responseBody = joinPoint.proceed();
        logger.info(responseBody.toString());
        return responseBody;
    }

    @Around("@annotation(cn.zhangjingyao.zjyrdf.annotation.SystemLog))")
    public Object writeSystemLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long requestTime = System.currentTimeMillis();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AccessToken accessToken = customTokenServices.getAccessToken(authentication);
        User user = (User) accessToken.getAdditionalInformation().get("user");
        cn.zhangjingyao.zjyrdf.annotation.SystemLog annotation = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName()).getAnnotation(cn.zhangjingyao.zjyrdf.annotation.SystemLog.class);
        logger.info(annotation.value());
        logger.info(user.getAccount() + ":" + request.getServletPath() + " - " + JSON.toJSON(request.getParameterMap()));
        Object responseBody = joinPoint.proceed();
        logger.info(responseBody.toString());

        SystemLog systemLog = new SystemLog("zjyrdf", requestTime, System.currentTimeMillis(), IpUtil.getIpAddress(request), user.getUserId(), user.getUserName(), user.getAccount(), annotation.value(), request.getServletPath(), JSON.toJSON(request.getParameterMap()).toString(), responseBody.toString());
        producerService.send("TopicLog", "zjyrdf", JSON.toJSON(systemLog).toString());

        return responseBody;
    }

}



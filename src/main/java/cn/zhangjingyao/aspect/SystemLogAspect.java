package cn.zhangjingyao.aspect;

import cn.zhangjingyao.annotation.SystemLog;
import cn.zhangjingyao.entity.system.User;
import cn.zhangjingyao.security.service.CustomTokenServices;
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

/**
 * @author
 */
@Component
@Aspect
public class SystemLogAspect {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    CustomTokenServices customTokenServices;

    @Around("execution(* cn.zhangjingyao.controller..*.*(..)) && !@annotation(cn.zhangjingyao.annotation.SystemLog))")
    public Object writeSimpleLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AccessToken accessToken = customTokenServices.getAccessToken(authentication);
        User user = (User) accessToken.getAdditionalInformation().get("user");
        logger.info(user.getAccount() + ":" + request.getServletPath() + " - " + JSON.toJSON(request.getParameterMap()));
        Object result = joinPoint.proceed();
        logger.info(result.toString());
        return result;
    }

    @Around("@annotation(cn.zhangjingyao.annotation.SystemLog))")
    public Object writeSystemLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AccessToken accessToken = customTokenServices.getAccessToken(authentication);
        User user = (User) accessToken.getAdditionalInformation().get("user");
        SystemLog annotation = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName()).getAnnotation(SystemLog.class);
        logger.info(annotation.value());
        logger.info(user.getAccount() + ":" + request.getServletPath() + " - " + JSON.toJSON(request.getParameterMap()));
        Object result = joinPoint.proceed();
        logger.info(result.toString());
        return result;
    }

}



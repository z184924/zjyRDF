package cn.zhangjingyao.interceptor;

import cn.zhangjingyao.security.service.CustomTokenServices;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接口调用日志拦截
 *
 * @author
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    CustomTokenServices customTokenServices;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        if (header != null && header.toLowerCase().startsWith("bearer ")) {
            String accessToken = header.substring(7);
            OAuth2Authentication authentication = customTokenServices.loadAuthentication(accessToken);
            logger.info(authentication.getName() + ":" + request.getServletPath() + " - " + JSON.toJSON(request.getParameterMap()));
        } else {
            logger.info(request.getServletPath() + " - " + JSON.toJSON(request.getParameterMap()));
        }
        return true;
    }
}

package cn.zhangjingyao.zjyrdf.interceptor;

import cn.zhangjingyao.zjyrdf.entity.system.User;
import cn.zhangjingyao.zjyrdf.exception.CustomException;
import cn.zhangjingyao.zjyrdf.security.service.CustomTokenServices;
import cn.zhangjingyao.zjyrdf.util.toekn.FormTokenPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 表单Token拦截
 *
 * @author
 */
public class FormInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private CustomTokenServices customTokenServices;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws CustomException {
        String servletPath = request.getServletPath();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] formTokens = parameterMap.get("formToken");
        if (formTokens == null || formTokens.length <= 0) {
            logger.info(servletPath + " - 未携带formToken");
            throw new CustomException("未携带formToken");
        }
        FormTokenPool formTokenPool = FormTokenPool.getInstance();
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AccessToken accessToken = customTokenServices.getAccessToken(authentication);
        User user = (User) accessToken.getAdditionalInformation().get("user");
        if (!formTokenPool.checkAndRemoveToken(user.getUserId(),formTokens[0])) {
            logger.info(servletPath + " - formToken:" + formTokens[0] + "无效");
            throw new CustomException("请勿重复提交表单");
        }
        return true;
    }
}

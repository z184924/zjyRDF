package cn.zhangjingyao.interceptor;

import cn.zhangjingyao.exception.CustomException;
import cn.zhangjingyao.util.toekn.FormTokenPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] formTokens = parameterMap.get("formToken");
        if(formTokens==null||formTokens.length<=0){
            logger.info(servletPath+" - 未携带formToken");
            throw new CustomException("未携带formToken");
        }
        FormTokenPool formTokenPool = FormTokenPool.getInstance();
        if(!formTokenPool.checkAndReomveToken(formTokens[0])){
            logger.info(servletPath+" - formToken:"+formTokens[0]+"无效");
            throw new CustomException("请勿重复提交表单");
        }
        return true;
    }
}

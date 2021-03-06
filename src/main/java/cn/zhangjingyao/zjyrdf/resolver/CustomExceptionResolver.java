package cn.zhangjingyao.zjyrdf.resolver;

import cn.zhangjingyao.zjyrdf.exception.CustomException;
import cn.zhangjingyao.zjyrdf.util.WriteJsonUtil;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 类名称：CustomExceptionResolver.java
 * 类描述： 异常处理代理
 *
 * @author
 * @version 1.0
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception exception) {
        // TODO Auto-generated method stub
        StringBuffer stringBuffer = new StringBuffer(System.lineSeparator());
        stringBuffer.append("===============异常开始===============");
        stringBuffer.append(System.lineSeparator());
        stringBuffer.append(exception.toString()+System.lineSeparator());
        for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
            stringBuffer.append(stackTraceElement.toString()+System.lineSeparator());
        }
        stringBuffer.append("===============异常结束===============");
        logger.error(stringBuffer.toString());
        HashMap<String, Object> resData = new HashMap<>(16);
        if (exception instanceof InvalidGrantException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            resData.put("state", "error");
            resData.put("error", HttpStatus.BAD_REQUEST.value());
            resData.put("errorMessage", exception.getMessage());
            resData.put("message", "用户名密码错误或无效令牌");
        } else if (exception instanceof CustomException) {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
            resData.put("state", "error");
            resData.put("error", HttpStatus.NOT_IMPLEMENTED.value());
            resData.put("errorMessage", exception.getMessage());
            resData.put("message", exception.getMessage());
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resData.put("state", "error");
            resData.put("error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            resData.put("errorMessage", exception.getMessage());
            resData.put("message", "操作失败");
        }
        WriteJsonUtil.writeJson(response, resData);
        ModelAndView mv = new ModelAndView();
        return mv;
    }

}

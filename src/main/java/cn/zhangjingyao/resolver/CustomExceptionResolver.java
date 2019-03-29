package cn.zhangjingyao.resolver;

import cn.zhangjingyao.exception.CustomException;
import cn.zhangjingyao.util.WriteJsonUtil;
import com.alibaba.fastjson.JSON;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名称：CustomExceptionResolver.java
 * 类描述： 异常处理代理
 *
 * @author
 * @version 1.0
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception exception) {
        // TODO Auto-generated method stub

        System.out.println("===============异常开始===============");
        exception.printStackTrace();
        System.out.println("===============异常结束===============");
        HashMap<String, Object> resData = new HashMap<>(16);
        if (exception instanceof InvalidGrantException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            resData.put("state","error");
            resData.put("error",HttpStatus.BAD_REQUEST.value());
            resData.put("errorMessage",exception.getMessage());
            resData.put("message","用户名密码错误或无效令牌");
        }else if(exception instanceof CustomException){
            response.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
            resData.put("state","error");
            resData.put("error",HttpStatus.NOT_IMPLEMENTED.value());
            resData.put("errorMessage",exception.getMessage());
            resData.put("message",exception.getMessage());
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resData.put("state","error");
            resData.put("error",HttpStatus.INTERNAL_SERVER_ERROR.value());
            resData.put("errorMessage",exception.getMessage());
            resData.put("message","操作失败");
        }
        WriteJsonUtil.writeJson(response, resData);
        ModelAndView mv = new ModelAndView();
        return mv;
    }

}

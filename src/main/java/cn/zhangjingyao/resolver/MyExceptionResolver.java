package cn.zhangjingyao.resolver;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 
* 类名称：MyExceptionResolver.java
* 类描述： 异常处理代理
* @author
 * @version 1.0
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

	@Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		/**
		System.out.println("==============异常开始=============");
		ex.printStackTrace();
		System.out.println("==============异常结束=============");
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
		*/
		
		
		System.out.println("==============异常开始=============");
		ex.printStackTrace();
		System.out.println("==============异常结束=============");
		response.setCharacterEncoding("UTF-8");
		if (!(request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
			// 如果不是异步请求
			// Apply HTTP status code for error views, if specified.
			// Only apply it if we're processing a top-level request.
			ModelAndView mv = new ModelAndView("error");
			mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
			return mv;
		} else {
			// JSON格式返回
			String json = "";	
			try {
			  	response.setStatus(HttpStatus.OK.value()); //设置状态码  
	            response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
	            response.setHeader("Cache-Control", "no-cache, must-revalidate");  
				Map<String,Object> res= new HashMap<String, Object>();
				res.put("state", "error");
				res.put("message", "操作失败");
				json = JSON.toJSONString(res);	
				PrintWriter writer = response.getWriter();
//				writer.write(ex.getMessage());
				writer.write(json);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(json);
			ModelAndView mv = new ModelAndView();
			return mv;
		}
		
	}
	
}

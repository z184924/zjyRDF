package cn.zhangjingyao.zjyrdf.controller.base;


import cn.zhangjingyao.zjyrdf.entity.PageData;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class BaseController {


	protected Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}

	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		return request;
	}
	/**
	 * 得到session对象
	 */
	public HttpSession getSession() {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	/**
	 * 返回常规json字符串
	 * @param state 状态码
	 * @param message 返回消息
	 * @return
	 */
	public String jsonContent(String state,String message) {
		Map<String,Object> res= new HashMap<String, Object>(16);
		res.put("state", state);
		res.put("message", message);
		return JSON.toJSONString(res);
	}
	/**
	 * 返回常规json字符串
	 * @param state 状态码
	 * @param pageData 返回数据
	 * @return
	 */
	public String jsonContent(String state,PageData pageData) {
		Map<String,Object> res= new HashMap<String, Object>(16);
		res.put("state", state);
		res.put("data", pageData);
		return JSON.toJSONString(res);
	}

	/**
	 * 返回分页json字符串
	 * @param state 状态码
	 * @param dataList 结果集
	 * @return
	 */
	public String jsonContent(String state, List<PageData> dataList) {
		Map<String,Object> res= new HashMap<String, Object>(16);
		res.put("state", state);
		res.put("data",dataList);
		return JSON.toJSONString(res);
	}

}

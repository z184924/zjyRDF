package cn.zhangjingyao.filter;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.util.DateUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 登录验证过滤器
 * @author
 */
public class UseSystemFilter extends BaseController implements Filter {

	/**
	 * 初始化
	 */
    @Override
    public void init(FilterConfig fc) throws ServletException {
		//FileUtil.createDir("d:/FH/topic/");
	}

    @Override
    public void destroy() {

	}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String useEndDate = "2017-01-14";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		if (DateUtil.compareDate(nowDate, useEndDate)) {
			response.sendRedirect("use_error.jsp");
			return;
		}


		chain.doFilter(req, res); // 调用下一过滤器
	}

}

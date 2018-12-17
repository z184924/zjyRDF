package cn.zhangjingyao.listener;

import cn.zhangjingyao.util.Const;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
* 类名称：WebAppContextListener.java
* 类描述： 
* @version 1.0
 */
public class WebAppContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

    @Override
    public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		Const.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		//System.out.println("========获取Spring WebApplicationContext");
	}

}

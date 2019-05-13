package cn.zhangjingyao.filter;


import cn.zhangjingyao.controller.base.BaseController;

import javax.servlet.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author
 */
public class StartFilter extends BaseController implements Filter {

	
	
	
	/**
	 * 初始化
	 */
    @Override
    public void init(FilterConfig fc) throws ServletException {
//		this.startWebsocketInstantMsg();
//		this.startWebsocketOnline();
	}
	
	/**
	 * 启动即时聊天服务
	 */
//	public void startWebsocketInstantMsg(){
//		WebSocketImpl.DEBUG = false;
//		ChatServer s;
//		try {
//			String strWEBSOCKET = Tools.readTxtFile(Const.WEBSOCKET);//读取WEBSOCKET配置,获取端口配置
//			if(null != strWEBSOCKET && !"".equals(strWEBSOCKET)){
//				String strIW[] = strWEBSOCKET.split(",fh,");
//				if(strIW.length == 4){
//					s = new ChatServer(Integer.parseInt(strIW[1]));
//					s.start();
//				}
//			}
//			//System.out.println( "websocket服务器启动,端口" + s.getPort() );
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 启动在线管理服务
	 */
//	public void startWebsocketOnline(){
//		WebSocketImpl.DEBUG = false;
//		OnlineChatServer s;
//		try {
//			String strWEBSOCKET = Tools.readTxtFile(Const.WEBSOCKET);//读取WEBSOCKET配置,获取端口配置
//			if(null != strWEBSOCKET && !"".equals(strWEBSOCKET)){
//				String strIW[] = strWEBSOCKET.split(",fh,");
//				if(strIW.length == 4){
//					s = new OnlineChatServer(Integer.parseInt(strIW[3]));
//					s.start();
//				}
//			}
//			//System.out.println( "websocket服务器启动,端口" + s.getPort() );
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	//计时器
	public void timer() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 9); // 控制时
		calendar.set(Calendar.MINUTE, 0); 		// 控制分
		calendar.set(Calendar.SECOND, 0); 		// 控制秒

		Date time = calendar.getTime(); 		// 得出执行任务的时间

		ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
            public void run() {

				//PersonService personService = (PersonService)ApplicationContext.getBean("personService");


				//System.out.println("-------设定要指定任务--------");
			}
		}, time.getTime(), 1000*60*60*24, TimeUnit.MILLISECONDS);// 这里设定将延时每天固定执行
	}


    @Override
    public void destroy() {
		// TODO Auto-generated method stub
		
	}


    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
}

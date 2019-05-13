package cn.zhangjingyao.util.toekn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author
 */
public class TokenPool extends ConcurrentHashMap<String,Token> {
	
	private static final long serialVersionUID = 1L;
	private static TokenPool tokenPool;
	private TokenPool() {}
	public static TokenPool getInstance() {
		if(tokenPool==null) {
			tokenPool=new TokenPool();
			cleanPoolTask();
		}
		return tokenPool;
	}
	public boolean addToken(Token token) {
		tokenPool.remove(token.getToken());
		tokenPool.put(token.getToken(), token);
		return true;
	}
	public Token getToken(String tokenStr) {
		return tokenPool.get(tokenStr);
	}
	public boolean flushToken(String tokenStr) {
		Token token = tokenPool.getToken(tokenStr);
		token.flushExpiryTime();
		tokenPool.remove(tokenStr);
		tokenPool.addToken(token);
		return true;
	}
	
	private static boolean  cleanPoolTask() {
		ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cleanPool();
			}
		};
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date startTime=simpleDateFormat.parse("2017-12-01 00:00:00");
			scheduledExecutorService.scheduleAtFixedRate(task,startTime.getTime(), 24*60*60*1000, TimeUnit.MILLISECONDS);
		}catch (Exception e) {
			System.out.println("TokenPool定时清理任务设置出现异常");
			return false;
		}
		return true;
	}
	
	public static boolean cleanPool() {
		if(tokenPool!=null) {
			System.out.println("TokenPool Clean Start");
			Set<String> keySet = tokenPool.keySet();
			for (String tokenStr : keySet) {
				Token token = tokenPool.get(tokenStr);
				if(token.isExpiry()) {
					tokenPool.remove(tokenStr);
				}
			}
		}
		return true;
	}

}

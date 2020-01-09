package cn.zhangjingyao.zjyrdf.util.toekn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 */
@Component
@EnableScheduling
public class TokenPool extends ConcurrentHashMap<String,Token> {

	private Logger logger= LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private static TokenPool tokenPool;

	private TokenPool() {}
	public static TokenPool getInstance() {
		if(tokenPool==null) {
			tokenPool=new TokenPool();
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

	/**
	 * 定时清理过期Token
	 * @return
	 */
	@Scheduled(cron = "0 0 3 * * *")
	public boolean cleanPool() {
		if(tokenPool!=null) {
			logger.info("TokenPool Clean Start");
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

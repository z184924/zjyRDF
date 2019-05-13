package cn.zhangjingyao.util.toekn;

import cn.zhangjingyao.entity.system.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * @author
 */
public class Token {
	private String token;
	private User user;
	private Timestamp expiryTime;
	
	public Token() {
		this.token=this.get32UUID();
		expiryTime=new Timestamp(System.currentTimeMillis()+(30*60*1000));
	}
	
	public Token(User user) {
		this.user=user;
		this.token=this.get32UUID();
		expiryTime=new Timestamp(System.currentTimeMillis()+(30*60*1000));
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(Timestamp expiryTime) {
		this.expiryTime = expiryTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 判断是否逾期,已逾期返回true
	 * @return
	 */
	public boolean isExpiry() {
		return this.expiryTime.before(new Date());
	}
	/**
	 * 刷新超时时间
	 * @return
	 */
	public boolean flushExpiryTime() {
		expiryTime=new Timestamp(System.currentTimeMillis()+(30*60*1000));
		return true;
	}
	private String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
}

package cn.zhangjingyao.entity.system;

import java.io.Serializable;

/**
 * 
* 类名称：User.java
* 类描述： 
* @version 1.0
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;		//用户id
	private String account;		//登录账号
	private String userName;	//用户名
	private String password; 	//密码

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}


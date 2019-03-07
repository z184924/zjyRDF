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
	private String password; 	//密码
	private String userName;	//用户名
	private Boolean locked;		//是否锁定
	private Boolean disable;	//是否禁用
	private String specialRole; //特殊角色

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	public String getSpecialRole() {
		return specialRole;
	}

	public void setSpecialRole(String specialRole) {
		this.specialRole = specialRole;
	}
}


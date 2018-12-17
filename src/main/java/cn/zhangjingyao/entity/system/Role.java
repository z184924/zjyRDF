package cn.zhangjingyao.entity.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
* 类名称：Role.java
* 类描述： 

* @version 1.0
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ROLE_ID;
	private String ROLE_NAME;
	private Integer SORTNUM;
	private String REMARK;
	private String COMTY_ID;
	

	public String getROLE_ID() {
		return ROLE_ID;
	}


	public void setROLE_ID(String ROLE_ID) {
		this.ROLE_ID = ROLE_ID;
	}


	public String getROLE_NAME() {
		return ROLE_NAME;
	}


	public void setROLE_NAME(String ROLE_NAME) {
		this.ROLE_NAME = ROLE_NAME;
	}


	public Integer getSORTNUM() {
		return SORTNUM;
	}


	public void setSORTNUM(Integer SORTNUM) {
		this.SORTNUM = SORTNUM;
	}


	public String getREMARK() {
		return REMARK;
	}


	public void setREMARK(String REMARK) {
		this.REMARK = REMARK;
	}


	public String getCOMTY_ID() {
		return COMTY_ID;
	}


	public void setCOMTY_ID(String COMTY_ID) {
		this.COMTY_ID = COMTY_ID;
	}


	/**
	 * 得到权限list
	 * @return
	 */
	public static List<String> getOperButton() {
		List<String> operButtonList = new ArrayList<String>();
		return operButtonList;

	}
	
	
}

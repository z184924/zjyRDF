package cn.zhangjingyao.entity.system;

import java.io.Serializable;
import java.util.List;

/**
 * 
* 类名称：Menu.java
* 类描述： 
*
* @version 1.0
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String MENU_ID;
	private String MENU_NAME;
	private String MENU_URL;
	private String PARENT_ID;
	private String SORTNUM;
	private String MENU_ICON;
	private String REMARK;
	private String IFHTTP;
	private String IFABLE;
	private String systemId;
	private String target;
	
	private Menu parentMenu;
	private List<Menu> subMenu;
	
	private boolean hasMenu = false;

	public String getMENU_ID() {
		return MENU_ID;
	}

	public void setMENU_ID(String mENU_ID) {
		MENU_ID = mENU_ID;
	}

	public String getMENU_NAME() {
		return MENU_NAME;
	}

	public void setMENU_NAME(String mENU_NAME) {
		MENU_NAME = mENU_NAME;
	}

	public String getMENU_URL() {
		return MENU_URL;
	}

	public void setMENU_URL(String mENU_URL) {
		MENU_URL = mENU_URL;
	}

	public String getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}

	public String getSORTNUM() {
		return SORTNUM;
	}

	public void setSORTNUM(String sORTNUM) {
		SORTNUM = sORTNUM;
	}

	public String getMENU_ICON() {
		return MENU_ICON;
	}

	public void setMENU_ICON(String mENU_ICON) {
		MENU_ICON = mENU_ICON;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getIFHTTP() {
		return IFHTTP;
	}

	public void setIFHTTP(String iFHTTP) {
		IFHTTP = iFHTTP;
	}

	public String getIFABLE() {
		return IFABLE;
	}

	public void setIFABLE(String iFABLE) {
		IFABLE = iFABLE;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}

	public boolean isHasMenu() {
		return hasMenu;
	}

	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}

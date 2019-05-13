package cn.zhangjingyao.entity.system;

import cn.zhangjingyao.entity.PageData;

import java.io.Serializable;

/**
 * @author 类名称：User.java
 * 类描述：
 * @version 1.0
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 是否锁定
     */
    private Boolean locked;
    /**
     * 是否禁用
     */
    private Boolean disable;
    /**
     * 特殊角色
     */
    private String specialRole;

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

    public PageData user2PageData() {
        PageData pageData = new PageData();
        pageData.put("userId", this.userId);
        pageData.put("account", this.account);
        pageData.put("password", this.password);
        pageData.put("userName", this.userName);
        pageData.put("locked", this.locked);
        pageData.put("disable", this.disable);
        pageData.put("specialRole", this.specialRole);
        return pageData;
    }

    public User() {

    }

    public User(PageData pageData) {
        this.userId = pageData.getString("userId");
        this.account = pageData.getString("account");
        this.password = pageData.getString("password");
        this.userName = pageData.getString("userName");
        this.specialRole = pageData.getString("specialRole");
        this.locked = (Boolean) pageData.get("locked");
        this.disable = (Boolean) pageData.get("disable");
    }
}


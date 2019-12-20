package cn.zhangjingyao.zjyrdf.entity.log;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 系统日志
 *
 * @author
 */
public class SystemLog {
    private String systemTag;
    private long requestTime;
    private long responseTime;
    private String ip;
    private String userId;
    private String userName;
    private String account;
    private String annotation;
    private String requestPath;
    private String requestParameter;
    private String responseBody;

    public SystemLog() {

    }

    public SystemLog(String systemTag, long requestTime, long responseTime, String ip, String userId, String userName, String account, String annotation, String requestPath, String requestParameter, String responseBody) {
        this.systemTag = systemTag;
        this.requestTime = requestTime;
        this.responseTime = responseTime;
        this.ip = ip;
        this.userId = userId;
        this.userName = userName;
        this.account = account;
        this.annotation = annotation;
        this.requestPath = requestPath;
        this.requestParameter = requestParameter;
        this.responseBody = responseBody;
    }

    public String getSystemTag() {
        return systemTag;
    }

    public void setSystemTag(String systemTag) {
        this.systemTag = systemTag;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRequestParameter() {
        return requestParameter;
    }

    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

}

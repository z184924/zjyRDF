package cn.zhangjingyao.util;

/**
 * @author 常量类
 */
public class Const {
    public static final String TRUE = "true";
    public static final String FALUSE = "false";
    public static final String SESSION_USER = "sessionUser";
    /**
     * 登录地址
     */
    public static final String LOGIN = "/login_toLogin";
    /**
     * 分页条数配置路径
     */
    public static final int PAGE_SHOW_COUNT = 10;
    /**
     * 不对匹配该值的访问路径拦截（正则）
     */
    public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(createCode)|(static)|(favicon)|(error)).*";
    /**
     * 对匹配该值的访问路径采用Token验证（正则）
     */
    public static final String TOKEN_INTERCEPTOR_PATH = ".*/((api)|(pdapi)).*";
    public static final String DEFAULT_RESOURCE_ID = "defaultResource";
}

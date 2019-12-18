package cn.zhangjingyao.zjyrdf.controller.system;


import cn.zhangjingyao.zjyrdf.annotation.SystemLog;
import cn.zhangjingyao.zjyrdf.controller.base.BaseController;
import cn.zhangjingyao.zjyrdf.entity.PageData;
import cn.zhangjingyao.zjyrdf.security.service.CustomTokenServices;
import cn.zhangjingyao.zjyrdf.util.toekn.FormTokenPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private CustomTokenServices customTokenServices;

    /**
     * 注销
     *
     * @return state:注销状态
     */
    @SystemLog("注销")
    @ResponseBody
    @RequestMapping(value = "/oauth/logout")
    public String oauthLogout() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AccessToken accessToken = customTokenServices.getAccessToken(authentication);
        customTokenServices.revokeToken(accessToken.getValue());
        return this.jsonContent("success", "注销成功");
    }

    /**
     * 获取表单Token
     *
     * @return formToken:表单Token
     */
    @SystemLog("获取表单Token")
    @ResponseBody
    @RequestMapping(value = "/formToken")
    public String formToken() {
        FormTokenPool formTokenPool = FormTokenPool.getInstance();
        String formToken = formTokenPool.addToken(this.getCurrentUser().getUserId());
        PageData res = new PageData();
        res.put("formToken", formToken);
        return this.jsonContent("success", res);
    }

}

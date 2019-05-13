package cn.zhangjingyao.controller.system;


import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.security.service.CustomTokenServices;
import cn.zhangjingyao.util.toekn.FormTokenPool;
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
    @RequestMapping(value = "/oauth/logout")
    @ResponseBody
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
    @RequestMapping(value = "/formToken")
    @ResponseBody
    public String formToken() {
        FormTokenPool formTokenPool = FormTokenPool.getInstance();
        String formToken = formTokenPool.addToken();
        PageData res = new PageData();
        res.put("formToken", formToken);
        return this.jsonContent("success", res);
    }

}

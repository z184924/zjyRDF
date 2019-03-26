package cn.zhangjingyao.security.service;

import cn.zhangjingyao.util.Logger;
import cn.zhangjingyao.util.RightsCache;
import cn.zhangjingyao.util.RightsHelper;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * @author
 */
@Component("rbacService")
public class RbacService {
    Logger logger=Logger.getLogger(this.getClass());

    @Autowired
    CustomTokenServices customTokenServices;
    public boolean hasPermission(HttpServletRequest request, OAuth2Authentication authentication){
        OAuth2AccessToken accessToken = customTokenServices.getAccessToken(authentication);
        String requestURL=request.getServletPath();
        RightsCache rightsCache = RightsCache.getInstance();
        Integer rightsId = rightsCache.get(requestURL);
        BigInteger rightsCode = (BigInteger)accessToken.getAdditionalInformation().get("rightsCode");
        return RightsHelper.testRights(rightsCode,rightsId);
    }
}

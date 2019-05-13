package cn.zhangjingyao.security.service;

import cn.zhangjingyao.util.RightsCache;
import cn.zhangjingyao.util.RightsHelper;
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

    @Autowired
    CustomTokenServices customTokenServices;
    public boolean hasPermission(HttpServletRequest request, OAuth2Authentication authentication){
        OAuth2AccessToken accessToken = customTokenServices.getAccessToken(authentication);
        String requestURL=request.getServletPath();
        RightsCache rightsCache = RightsCache.getInstance();
        Integer rightsId = rightsCache.get(requestURL);
        BigInteger rightsCode = (BigInteger)accessToken.getAdditionalInformation().get("rightsCode");
        if(rightsId!=null&&rightsCode!=null){
            return RightsHelper.testRights(rightsCode,rightsId);
        }else{
            return false;
        }
    }
}

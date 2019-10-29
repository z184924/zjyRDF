package cn.zhangjingyao.security.service;

import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import cn.zhangjingyao.service.system.RoleService;
import cn.zhangjingyao.service.system.UserService;
import cn.zhangjingyao.util.RightsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class CustomTokenServices extends DefaultTokenServices {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public CustomTokenServices(TokenStore tokenStore) {
        this.setTokenEnhancer(new CustomTokenEnhancer());
        this.setTokenStore(tokenStore);
        this.setSupportRefreshToken(true);
        //AccessToken有效期自定义设置，默认12小时
        this.setAccessTokenValiditySeconds(30 * 60);
        //RefreshToken有效期自定义设置，默认30天
        this.setRefreshTokenValiditySeconds(24 * 60 * 60);
    }


    public class CustomTokenEnhancer implements TokenEnhancer {

        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);
                replaceShotLine(token);
                try {
                    Map<String, Object> additionalInformation = new HashMap<String, Object>(16);
                    //放入用户信息
                    PageData userInfo = userService.findByAccount(authentication.getName());
                    User user = new User(userInfo);
                    additionalInformation.put("user", user);
                    //放入权限码
                    List<PageData> rightsData = roleService.listUserRights(user.getUserId());
                    int[] rightsIds = new int[rightsData.size()];
                    int i = 0;
                    for (PageData rights : rightsData) {
                        rightsIds[i] = rights.getInt("rightsId");
                        i++;
                    }
                    BigInteger rightsCode = RightsHelper.sumRights(rightsIds);
                    additionalInformation.put("rightsCode", rightsCode);
                    token.setAdditionalInformation(additionalInformation);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return token;
            }
            return accessToken;
        }

        private DefaultOAuth2AccessToken replaceShotLine(DefaultOAuth2AccessToken token) {
            token.setValue(token.getValue().replace("-", ""));
            token.setRefreshToken(new DefaultOAuth2RefreshToken(token.getRefreshToken().getValue().replace("-", "")));
            return token;
        }

    }
}

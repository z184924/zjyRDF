package cn.zhangjingyao.security.service;

import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        PageData searchPd = new PageData();
        searchPd.put("account", account);
        PageData userPageData = userService.findByAccount(searchPd);
        if (userPageData == null) {
            throw new UsernameNotFoundException("账号:" + account + "不存在");
        }
        cn.zhangjingyao.entity.system.User entityUser = new cn.zhangjingyao.entity.system.User(userPageData);
        User user = new User(entityUser.getAccount(), entityUser.getPassword(), !entityUser.getDisable(), true, true, !entityUser.getLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList(entityUser.getSpecialRole()));
        return user;
    }
}

package cn.zhangjingyao.interceptor.shiro;


import cn.zhangjingyao.entity.PageData;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//import cn.zhangjingyao.service.system.role.RoleService;

/**
 * @author
 */
public class ShiroRealm extends AuthorizingRealm {




//	  @Resource(name="roleService")
//	  private RoleService roleService;

	  @Override
      protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
	    throws AuthenticationException
	  {
	    String username = (String)token.getPrincipal();
	    String password = new String((char[])token.getCredentials());

	    if ((username != null) && (password != null)) {
	      return new SimpleAuthenticationInfo(username, password, getName());
	    }
	    return null;
	  }

	  @Override
      protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc)
	  {
	    try
	    {
	      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	      String userName = (String)pc.getPrimaryPrincipal();
	      PageData pd = new PageData();
	      pd.put("USERNAME", userName);

//	      PageData user = this.userService.findByUId(pd);

//	      String ROLE_ID = (String)user.get("ROLE_ID");

//	      if ((ROLE_ID != null) && (!"".equals(ROLE_ID))) {
//	        Role ROLE = this.roleService.findRoleById(ROLE_ID);
//	        info.addRole(ROLE.getROLE_NAME());
//	      }
	      return info;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

}

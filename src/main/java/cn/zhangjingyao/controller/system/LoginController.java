package cn.zhangjingyao.controller.system;


import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import cn.zhangjingyao.service.system.user.UserService;
import cn.zhangjingyao.util.Const;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/*
 * 总入口
 */
@Controller
public class LoginController extends BaseController {

	@Resource
	private UserService userService;

	/**
	 * 用户登录
	 * @param username String,用户名
	 * @param password String,密码
	 * @return token:令牌，user:用户信息，state:状态码
	 */
	@RequestMapping(value="/login")
	public String login()throws Exception{
//		if(License.licenseCheck()==false){
//			return	this.jsonContent("error","license error");
//		}
		PageData pd = this.getPageData();
		String password = new SimpleHash("SHA-1", pd.getString("account"), pd.getString("password")).toString();	//密码加密
		PageData searchPd= new PageData();
		searchPd.put("account",pd.getString("account"));
		searchPd.put("password",password);
		User user = this.userService.loginUser(searchPd);
		if(user==null){
			return "html/login";
		}else{
			HttpSession session = this.getSession();
			session.setAttribute(Const.SESSION_USER, user);
			PageData resultPd=new PageData();
			resultPd.put("user",user);
			return "html/index";
		}
	}

	/**
	 * 注销
	 * @param token String,需要注销的session
	 * @return state:注销状态
	 */
	@RequestMapping(value="/logout")
	public String logout()throws Exception{
		this.getSession().invalidate();
		return "html/login";
	}

	/**
	 * 前往用户登录页
	 */
	@RequestMapping(value="/login_toLogin")
	public String toLogin()throws Exception{
		return "html/login";
	}

	/**
	 * 前往用户index页
	 */
	@RequestMapping(value="/index")
	public String toIndex()throws Exception{
		return "html/index";
	}
}

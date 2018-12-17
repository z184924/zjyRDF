package cn.zhangjingyao.controller.api.system;


import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.system.User;
import cn.zhangjingyao.service.system.user.UserService;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.util.Token;
import cn.zhangjingyao.util.TokenPool;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/*
 * 总入口
 */
@Controller
@RequestMapping(value="/api")
public class ApiLoginController extends BaseController {

	@Resource
	private UserService userService;

	/**
	 * 用户登录
	 * @param username String,用户名
	 * @param password String,密码
	 * @return token:令牌，user:用户信息，state:状态码
	 */
	@RequestMapping(value="/login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object login()throws Exception{
//		if(License.licenseCheck()==false){
//			return	this.jsonContent("error","license error");
//		}
		PageData pd = this.getPageData();
		String password = new SimpleHash("SHA-1", pd.getString("account"), pd.getString("password")).toString();	//密码加密
		PageData searchPd= new PageData();
		searchPd.put("account",pd.getString("account"));
		searchPd.put("password",password);
//		searchPd.put("password",pd.getString("password"));
		User user = this.userService.loginUser(searchPd);
		if(user==null){
			return this.jsonContent("error", "该用户不存在或密码错误");
		}
		PageData resultPD=new PageData();
		TokenPool tokenPool= TokenPool.getInstance();
		Token token = new Token(user);
		tokenPool.addToken(token);
		resultPD.put("token", token.getToken());
		System.out.println("addToken:"+token.getToken());
		resultPD.put("user", user);
		return this.jsonContent("success",resultPD);

	}

	/**
	 * 注销
	 * @param token String,需要注销的token
	 * @return state:注销状态
	 */
	@ResponseBody
	@RequestMapping(value="/logout" ,produces="application/json;charset=UTF-8")
	public String logout()throws Exception{
		PageData pd = this.getPageData();
		String token = pd.getString("token");
		PageData resultPD=new PageData();
		TokenPool tokenPool=TokenPool.getInstance();
		tokenPool.remove(token);
		System.out.println("removeToken:"+token);
		return this.jsonContent("success",resultPD);
	}
	
	
}

package cn.zhangjingyao.controller.api.system;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.service.system.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/api/user")
public class ApiUserController extends BaseController {

	@Resource
	private UserService userService;

	
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() throws Exception{
		logBefore(logger, "新增User");
		PageData pd = this.getPageData();
		this.userService.save(pd);
//		for(int i=0;i<3;i++){
//			List<PageData> list =new ArrayList<>();
//			list.add(pd);
//			this.userService.save(list);
//		}
		return this.jsonContent("success", "保存成功");
	}
	

}

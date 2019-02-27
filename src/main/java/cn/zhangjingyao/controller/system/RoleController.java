package cn.zhangjingyao.controller.system;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.zhangjingyao.service.system.RoleService;

/** 
 * 类名称：RoleController
 * 创建时间：2019-02-27
 */
@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController {

	@Resource(name="roleService")
	private RoleService roleService;
	
	/**
	 * 新增或编辑
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() throws Exception{
		logBefore(logger, "新增或编辑Role");
		PageData pd = this.getPageData();
		if(pd.get("roleId")==null||"".equals(pd.get("roleId"))){
			pd.put("roleId", this.get32UUID()); // 主键
			this.roleService.save(pd);
		}else {
			this.roleService.edit(pd);
		}
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() throws Exception{
		logBefore(logger, "新增Role");
		PageData pd = this.getPageData();
		pd.put("roleId", this.get32UUID()); // 主键
		this.roleService.save(pd);
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete() throws Exception{
		logBefore(logger, "删除Role");
		PageData pd = this.getPageData();
		roleService.delete(pd);
		return this.jsonContent("success", "删除成功");
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/edit", produces = "application/json;charset=UTF-8")
	public String edit() throws Exception{
		logBefore(logger, "修改Role");
		PageData pd = this.getPageData();
		this.roleService.edit(pd);
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 分页查询列表
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getGridListJson", produces = "application/json;charset=UTF-8")
	public Object getGridListJson() throws Exception {
		logBefore(logger, "获取Role列表Json");
		PageData pd = this.getPageData();
		PageInfo<PageData> pageInfo = this.roleService.listPage(pd);// 分页查询列表
    	return this.jsonContent("success",pageInfo);
	}
	
	/**
	 * 获取表单页面JSON
	 */
	@RequestMapping(value = "/getFormJson", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getFormJson() throws Exception {
		logBefore(logger, "获取新建页面数据");
		PageData pd = this.getPageData();
		PageData resultPD = this.roleService.findById(pd);
		return this.jsonContent("success",resultPD);
	}
}

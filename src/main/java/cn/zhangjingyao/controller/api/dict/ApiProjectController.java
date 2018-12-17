package cn.zhangjingyao.controller.api.dict;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.Page;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.service.dict.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/** 
 * 类名称：ProjectController
 * 创建时间：2018-08-08
 */
@Controller
@RequestMapping(value="/api/project")
public class ApiProjectController extends BaseController {

	@Resource
	private ProjectService projectService;
	
	/**
	 * 新增或编辑
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() throws Exception{
		logBefore(logger, "新增或编辑Project");
		PageData pd = this.getPageData();
		if(pd.get("id")==null|| "".equals(pd.get("id"))) {
			pd.put("id", this.get32UUID()); // 主键
			this.projectService.save(pd);
		}else {
			this.projectService.edit(pd);
		}
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() throws Exception{
		logBefore(logger, "新增Project");
		PageData pd = this.getPageData();
		pd.put("id", this.get32UUID()); // 主键
		this.projectService.save(pd);
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete() throws Exception{
		logBefore(logger, "删除Project");
		PageData pd = this.getPageData();
		projectService.delete(pd);
		return this.jsonContent("success", "删除成功");
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/edit", produces = "application/json;charset=UTF-8")
	public String edit() throws Exception{
		logBefore(logger, "修改Project");
		PageData pd = this.getPageData();
		this.projectService.edit(pd);
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 返回列表JSON
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getGridListJson", produces = "application/json;charset=UTF-8")
	public Object getGridListJson() throws Exception {
		logBefore(logger, "获取Project列表Json");
		PageData pd = this.getPageData();
		PageInfo<PageData> pageInfo = this.projectService.listPage(pd);// 分页查询列表
		return this.jsonContent("success",pageInfo);
	}
	/**
	 * 返回列表JSON
	 *
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
	public Object listAll() throws Exception {
		logBefore(logger, "获取Project列表Json");
		PageData pd = this.getPageData();
		List<PageData> resultList = this.projectService.listAll(pd);
		return this.jsonContent("success",resultList);
	}
	/**
	 * 返回列表JSON
	 *
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/listAllCity", produces = "application/json;charset=UTF-8")
	public Object listAllCity() throws Exception {
		logBefore(logger, "获取Project列表Json");
		PageData pd = this.getPageData();
		List<PageData> resultList = this.projectService.listAllCity(pd);
		return this.jsonContent("success",resultList);
	}

	/**
	 * 获取表单页面JSON
	 */
	@RequestMapping(value = "/getFormJson", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getFormJson() throws Exception {
		logBefore(logger, "获取新建页面数据");
		PageData pd = this.getPageData();
		PageData resultPD = this.projectService.findById(pd);
		return this.jsonContent("success",resultPD);
	}
	
}

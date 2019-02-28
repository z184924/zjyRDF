package cn.zhangjingyao.controller.${packageName};

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.zhangjingyao.service.${packageName}.${objectName}Service;

/** 
 * 类名称：${objectName}Controller
 * 创建时间：${nowDate?string("yyyy-MM-dd")}
 */
@Controller
@RequestMapping(value="/${objectNameFirstLower}")
public class ${objectName}Controller extends BaseController {

	@Resource(name="${objectNameFirstLower}Service")
	private ${objectName}Service ${objectNameFirstLower}Service;
	
	/**
	 * 新增或编辑
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() throws Exception{
		logBefore(logger, "新增或编辑${objectName}");
		PageData pd = this.getPageData();
		if(pd.get("${primaryKey}")==null||"".equals(pd.get("${primaryKey}"))){
			//添加主键
			pd.put("${primaryKey}", this.get32UUID());
			this.${objectNameFirstLower}Service.save(pd);
		}else {
			this.${objectNameFirstLower}Service.edit(pd);
		}
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() throws Exception{
		logBefore(logger, "新增${objectName}");
		PageData pd = this.getPageData();
		//添加主键
		pd.put("${primaryKey}", this.get32UUID());
		this.${objectNameFirstLower}Service.save(pd);
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete() throws Exception{
		logBefore(logger, "删除${objectName}");
		PageData pd = this.getPageData();
		${objectNameFirstLower}Service.delete(pd);
		return this.jsonContent("success", "删除成功");
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/edit", produces = "application/json;charset=UTF-8")
	public String edit() throws Exception{
		logBefore(logger, "修改${objectName}");
		PageData pd = this.getPageData();
		this.${objectNameFirstLower}Service.edit(pd);
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 分页查询列表
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/listPage", produces = "application/json;charset=UTF-8")
	public Object listPage() throws Exception {
		logBefore(logger, "获取${objectName}列表Json");
		PageData pd = this.getPageData();
		// 分页查询列表
		PageInfo<PageData> pageInfo = this.${objectNameFirstLower}Service.listPage(pd);
    	return this.jsonContent("success",pageInfo);
	}
	
	/**
	 * 根据ID查询单条数据
	 */
	@RequestMapping(value = "/findById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object findById() throws Exception {
		logBefore(logger, "根据ID获取${objectName}数据");
		PageData pd = this.getPageData();
		PageData resultPD = this.${objectNameFirstLower}Service.findById(pd);
		return this.jsonContent("success",resultPD);
	}
}
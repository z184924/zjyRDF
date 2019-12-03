package cn.zhangjingyao.controller.${packageName};

import cn.zhangjingyao.annotation.SystemLog;
import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.zhangjingyao.service.${packageName}.${className}Service;

/**
 * 类名称:${className}Controller
 * 创建时间:${.now?string("yyyy-MM-dd")}
 *
 * @author CodeGenerator
 */
@Controller
@RequestMapping(value = "/${objectName}")
public class ${className}Controller extends BaseController {

	@Resource(name = "${objectName}Service")
	private ${className}Service ${objectName}Service;

    /**
     * 新增或编辑${className}
     *
     * @return Json
     */
	@SystemLog("新增或编辑${className}")
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() {
		PageData pd = this.getPageData();
		if (pd.get("${primaryKeyColumn.columnName}") == null || "".equals(pd.get("${primaryKeyColumn.columnName}")) || 0 == pd.getInt("${primaryKeyColumn.columnName}")){
<#if primaryKeyColumn.dataType == 'String'>
			//添加主键
			pd.put("${primaryKeyColumn.columnName}", this.get32UUID());
</#if>
			//替换字段
			pd = this.replaceAttribute(pd);
			this.${objectName}Service.save(pd);
		} else {
			//替换字段
			pd = this.replaceAttribute(pd);
			this.${objectName}Service.edit(pd);
		}
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 新增${className}
     *
     * @return Json
     */
	@SystemLog("新增${className}")
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() {
		PageData pd = this.getPageData();
<#if primaryKeyColumn.dataType == 'String'>
		//添加主键
		pd.put("${primaryKeyColumn.columnName}", this.get32UUID());
</#if>
		//替换字段
		pd = this.replaceAttribute(pd);
		this.${objectName}Service.save(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 删除${className}
     *
     * @return Json
     */
	@SystemLog("删除${className}")
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete() {
		PageData pd = this.getPageData();
		${objectName}Service.delete(pd);
		return this.jsonContent("success", "删除成功");
	}

    /**
     * 修改${className}
     *
     * @return Json
     */
	@SystemLog("修改${className}")
	@ResponseBody
	@RequestMapping(value = "/edit", produces = "application/json;charset=UTF-8")
	public String edit() {
		PageData pd = this.getPageData();
		//替换字段
		pd = this.replaceAttribute(pd);
		this.${objectName}Service.edit(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 分页查询${className}列表
     *
     * @return Json
     */
	@SystemLog("分页查询${className}列表")
	@ResponseBody
	@RequestMapping(value = "/listPage", produces = "application/json;charset=UTF-8")
	public Object listPage() {
		PageData pd = this.getPageData();
		// 分页查询列表
		PageInfo<PageData> pageInfo = this.${objectName}Service.listPage(pd);
		return this.jsonContent("success", pageInfo);
	}

    /**
     * 根据ID查询${className}数据
     *
     * @return Json
     */
	@SystemLog("根据ID查询${className}数据")
	@ResponseBody
	@RequestMapping(value = "/findById", produces = "application/json;charset=UTF-8")
	public Object findById() {
		PageData pd = this.getPageData();
		PageData resultPD = this.${objectName}Service.findById(pd);
		return this.jsonContent("success", resultPD);
	}

    /**
     * 替换字段
     *
     * @param pd 需替换PageData
     * @return 替换后PageData
     */
	private PageData replaceAttribute(PageData pd) {
	<#list columnList as column>
		<#if column.dataType == "Boolean">
		if ("true".equals(pd.getString("${column.columnName}"))) {
			pd.put("${column.columnName}", true);
		} else {
			pd.put("${column.columnName}", false);
		}
		</#if>
	</#list>
		return pd;
	}
}

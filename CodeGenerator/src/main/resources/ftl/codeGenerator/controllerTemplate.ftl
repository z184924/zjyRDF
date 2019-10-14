package cn.zhangjingyao.controller.${packageName};

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
 * @author
 */
@Controller
@RequestMapping(value = "/${objectName}")
public class ${className}Controller extends BaseController {

	@Resource(name = "${objectName}Service")
	private ${className}Service ${objectName}Service;

    /**
     * 新增或编辑
     *
     * @return Json
     */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() {
		logger.info("新增或编辑${className}");
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
     * 新增
     *
     * @return Json
     */
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() {
		logger.info("新增${className}");
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
     * 删除
     *
     * @return Json
     */
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete() {
		logger.info("删除${className}");
		PageData pd = this.getPageData();
		${objectName}Service.delete(pd);
		return this.jsonContent("success", "删除成功");
	}

    /**
     * 修改
     *
     * @return Json
     */
	@ResponseBody
	@RequestMapping(value = "/edit", produces = "application/json;charset=UTF-8")
	public String edit() {
		logger.info("修改${className}");
		PageData pd = this.getPageData();
		//替换字段
		pd = this.replaceAttribute(pd);
		this.${objectName}Service.edit(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 分页查询列表
     *
     * @return Json
     */
	@ResponseBody
	@RequestMapping(value = "/listPage", produces = "application/json;charset=UTF-8")
	public Object listPage() {
		logger.info("获取${className}列表Json");
		PageData pd = this.getPageData();
		// 分页查询列表
		PageInfo<PageData> pageInfo = this.${objectName}Service.listPage(pd);
		return this.jsonContent("success", pageInfo);
	}

    /**
     * 根据ID查询单条数据
     *
     * @return Json
     */
	@RequestMapping(value = "/findById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object findById() {
		logger.info("根据ID获取${className}数据");
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

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
 * 类名称:${objectName}Controller
 * 创建时间:${nowDate?string("yyyy-MM-dd")}
 *
 * @author
 */
@Controller
@RequestMapping(value="/${objectNameFirstLower}")
public class ${objectName}Controller extends BaseController {

	@Resource(name="${objectNameFirstLower}Service")
	private ${objectName}Service ${objectNameFirstLower}Service;

    /**
     * 新增或编辑
     * @return Json
     * @throws Exception Exception
     */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() throws Exception{
		logger.info("新增或编辑${objectName}");
		PageData pd = this.getPageData();
		if(pd.get("${primaryKey}")==null||"".equals(pd.get("${primaryKey}"))){
			//添加主键
			pd.put("${primaryKey}", this.get32UUID());
			//替换字段
			pd=this.replaceAttribute(pd);
			this.${objectNameFirstLower}Service.save(pd);
		}else {
			//替换字段
			pd=this.replaceAttribute(pd);
			this.${objectNameFirstLower}Service.edit(pd);
		}
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 新增
     * @return Json
     * @throws Exception Exception
     */
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() throws Exception{
		logger.info("新增${objectName}");
		PageData pd = this.getPageData();
		//添加主键
		pd.put("${primaryKey}", this.get32UUID());
		//替换字段
		pd=this.replaceAttribute(pd);
		this.${objectNameFirstLower}Service.save(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 删除
     * @return Json
     * @throws Exception Exception
     */
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete() throws Exception{
		logger.info("删除${objectName}");
		PageData pd = this.getPageData();
		${objectNameFirstLower}Service.delete(pd);
		return this.jsonContent("success", "删除成功");
	}

    /**
     * 修改
     * @return Json
     * @throws Exception Exception
     */
	@ResponseBody
	@RequestMapping(value="/edit", produces = "application/json;charset=UTF-8")
	public String edit() throws Exception{
		logger.info("修改${objectName}");
		PageData pd = this.getPageData();
		//替换字段
		pd=this.replaceAttribute(pd);
		this.${objectNameFirstLower}Service.edit(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 分页查询列表
     * @return Json
     * @throws Exception Exception
     */
	@ResponseBody
	@RequestMapping(value = "/listPage", produces = "application/json;charset=UTF-8")
	public Object listPage() throws Exception {
		logger.info("获取${objectName}列表Json");
		PageData pd = this.getPageData();
		// 分页查询列表
		PageInfo<PageData> pageInfo = this.${objectNameFirstLower}Service.listPage(pd);
		return this.jsonContent("success",pageInfo);
	}

    /**
     * 根据ID查询单条数据
     * @return Json
     * @throws Exception Exception
     */
	@RequestMapping(value = "/findById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object findById() throws Exception {
    	logger.info("根据ID获取${objectName}数据");
		PageData pd = this.getPageData();
		PageData resultPD = this.${objectNameFirstLower}Service.findById(pd);
		return this.jsonContent("success",resultPD);
	}

    /**
     * 替换字段
     * @param pd 需替换PageData
     * @return 替换后PageData
     * @throws Exception Exception
     */
	private PageData replaceAttribute(PageData pd) throws Exception{
	<#list fieldList as var>
		<#if var[1] == "Boolean">
		if("true".equals(pd.getString("${var[0]}"))){
			pd.put("${var[0]}",true);
		}else{
			pd.put("${var[0]}",false);
		}
		</#if>
	</#list>
		return pd;
	}
}

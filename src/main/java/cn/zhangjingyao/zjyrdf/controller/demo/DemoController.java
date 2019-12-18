package cn.zhangjingyao.zjyrdf.controller.demo;

import cn.zhangjingyao.zjyrdf.annotation.SystemLog;
import cn.zhangjingyao.zjyrdf.controller.base.BaseController;
import cn.zhangjingyao.zjyrdf.entity.PageData;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.zhangjingyao.zjyrdf.service.demo.DemoService;

/**
 * 类名称:DemoController
 * 创建时间:2019-12-03
 *
 * @author CodeGenerator
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController extends BaseController {

	@Resource(name = "demoService")
	private DemoService demoService;

    /**
     * 新增或编辑Demo
     *
     * @return Json
     */
	@SystemLog("新增或编辑Demo")
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() {
		PageData pd = this.getPageData();
		if (pd.get("demoId") == null || "".equals(pd.get("demoId")) || 0 == pd.getInt("demoId")){
			//添加主键
			pd.put("demoId", this.get32UUID());
			//替换字段
			pd = this.replaceAttribute(pd);
			this.demoService.save(pd);
		} else {
			//替换字段
			pd = this.replaceAttribute(pd);
			this.demoService.edit(pd);
		}
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 新增Demo
     *
     * @return Json
     */
	@SystemLog("新增Demo")
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() {
		PageData pd = this.getPageData();
		//添加主键
		pd.put("demoId", this.get32UUID());
		//替换字段
		pd = this.replaceAttribute(pd);
		this.demoService.save(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 删除Demo
     *
     * @return Json
     */
	@SystemLog("删除Demo")
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete() {
		PageData pd = this.getPageData();
		demoService.delete(pd);
		return this.jsonContent("success", "删除成功");
	}

    /**
     * 修改Demo
     *
     * @return Json
     */
	@SystemLog("修改Demo")
	@ResponseBody
	@RequestMapping(value = "/edit", produces = "application/json;charset=UTF-8")
	public String edit() {
		PageData pd = this.getPageData();
		//替换字段
		pd = this.replaceAttribute(pd);
		this.demoService.edit(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 分页查询Demo列表
     *
     * @return Json
     */
	@SystemLog("分页查询Demo列表")
	@ResponseBody
	@RequestMapping(value = "/listPage", produces = "application/json;charset=UTF-8")
	public Object listPage() {
		PageData pd = this.getPageData();
		// 分页查询列表
		PageInfo<PageData> pageInfo = this.demoService.listPage(pd);
		return this.jsonContent("success", pageInfo);
	}

    /**
     * 根据ID查询Demo数据
     *
     * @return Json
     */
	@SystemLog("根据ID查询Demo数据")
	@ResponseBody
	@RequestMapping(value = "/findById", produces = "application/json;charset=UTF-8")
	public Object findById() {
		PageData pd = this.getPageData();
		PageData resultPD = this.demoService.findById(pd);
		return this.jsonContent("success", resultPD);
	}

    /**
     * 替换字段
     *
     * @param pd 需替换PageData
     * @return 替换后PageData
     */
	private PageData replaceAttribute(PageData pd) {
		if ("true".equals(pd.getString("demoBoolean1"))) {
			pd.put("demoBoolean1", true);
		} else {
			pd.put("demoBoolean1", false);
		}
		if ("true".equals(pd.getString("demoBoolean2"))) {
			pd.put("demoBoolean2", true);
		} else {
			pd.put("demoBoolean2", false);
		}
		return pd;
	}
}

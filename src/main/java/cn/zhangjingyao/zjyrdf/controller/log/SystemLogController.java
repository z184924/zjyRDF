package cn.zhangjingyao.zjyrdf.controller.log;

import cn.zhangjingyao.zjyrdf.annotation.SystemLog;
import cn.zhangjingyao.zjyrdf.controller.base.BaseController;
import cn.zhangjingyao.zjyrdf.entity.PageData;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.zhangjingyao.zjyrdf.service.log.SystemLogService;

/**
 * 类名称:SystemLogController
 * 创建时间:2019-12-19
 *
 * @author CodeGenerator
 */
@Controller
@RequestMapping(value = "/systemLog")
public class SystemLogController extends BaseController {

	@Resource(name = "systemLogService")
	private SystemLogService systemLogService;

    /**
     * 新增或编辑SystemLog
     *
     * @return Json
     */
	@SystemLog("新增或编辑SystemLog")
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() {
		PageData pd = this.getPageData();
		if (pd.get("logId") == null || "".equals(pd.get("logId")) || 0 == pd.getInt("logId")){
			//替换字段
			pd = this.replaceAttribute(pd);
			this.systemLogService.save(pd);
		} else {
			//替换字段
			pd = this.replaceAttribute(pd);
			this.systemLogService.edit(pd);
		}
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 新增SystemLog
     *
     * @return Json
     */
	@SystemLog("新增SystemLog")
	@ResponseBody
	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public String save() {
		PageData pd = this.getPageData();
		//替换字段
		pd = this.replaceAttribute(pd);
		this.systemLogService.save(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 删除SystemLog
     *
     * @return Json
     */
	@SystemLog("删除SystemLog")
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete() {
		PageData pd = this.getPageData();
		systemLogService.delete(pd);
		return this.jsonContent("success", "删除成功");
	}

    /**
     * 修改SystemLog
     *
     * @return Json
     */
	@SystemLog("修改SystemLog")
	@ResponseBody
	@RequestMapping(value = "/edit", produces = "application/json;charset=UTF-8")
	public String edit() {
		PageData pd = this.getPageData();
		//替换字段
		pd = this.replaceAttribute(pd);
		this.systemLogService.edit(pd);
		return this.jsonContent("success", "保存成功");
	}

    /**
     * 分页查询SystemLog列表
     *
     * @return Json
     */
	@SystemLog("分页查询SystemLog列表")
	@ResponseBody
	@RequestMapping(value = "/listPage", produces = "application/json;charset=UTF-8")
	public Object listPage() {
		PageData pd = this.getPageData();
		// 分页查询列表
		PageInfo<PageData> pageInfo = this.systemLogService.listPage(pd);
		return this.jsonContent("success", pageInfo);
	}

    /**
     * 根据ID查询SystemLog数据
     *
     * @return Json
     */
	@SystemLog("根据ID查询SystemLog数据")
	@ResponseBody
	@RequestMapping(value = "/findById", produces = "application/json;charset=UTF-8")
	public Object findById() {
		PageData pd = this.getPageData();
		PageData resultPD = this.systemLogService.findById(pd);
		return this.jsonContent("success", resultPD);
	}

    /**
     * 替换字段
     *
     * @param pd 需替换PageData
     * @return 替换后PageData
     */
	private PageData replaceAttribute(PageData pd) {
		return pd;
	}
}

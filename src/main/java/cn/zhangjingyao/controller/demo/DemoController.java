package cn.zhangjingyao.controller.demo;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.zhangjingyao.service.demo.DemoService;

/**
 * 类名称:DemoController
 * 创建时间:2019-05-13
 *
 * @author
 */
@Controller
@RequestMapping(value="/demo")
public class DemoController extends BaseController {

	@Resource(name="demoService")
	private DemoService demoService;

    /**
     * 新增或编辑
     * @return Json
     * @throws Exception Exception
     */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
	public String saveOrUpdate() throws Exception{
		logger.info("新增或编辑Demo");
		PageData pd = this.getPageData();
		if(pd.get("demoId")==null||"".equals(pd.get("demoId"))){
			//添加主键
			pd.put("demoId", this.get32UUID());
			//替换字段
			pd=this.replaceAttribute(pd);
			this.demoService.save(pd);
		}else {
			//替换字段
			pd=this.replaceAttribute(pd);
			this.demoService.edit(pd);
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
		logger.info("新增Demo");
		PageData pd = this.getPageData();
		//添加主键
		pd.put("demoId", this.get32UUID());
		//替换字段
		pd=this.replaceAttribute(pd);
		this.demoService.save(pd);
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
		logger.info("删除Demo");
		PageData pd = this.getPageData();
		demoService.delete(pd);
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
		logger.info("修改Demo");
		PageData pd = this.getPageData();
		//替换字段
		pd=this.replaceAttribute(pd);
		this.demoService.edit(pd);
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
		logger.info("获取Demo列表Json");
		PageData pd = this.getPageData();
		// 分页查询列表
		PageInfo<PageData> pageInfo = this.demoService.listPage(pd);
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
    	logger.info("根据ID获取Demo数据");
		PageData pd = this.getPageData();
		PageData resultPD = this.demoService.findById(pd);
		return this.jsonContent("success",resultPD);
	}

    /**
     * 替换字段
     * @param pd 需替换PageData
     * @return 替换后PageData
     * @throws Exception Exception
     */
	private PageData replaceAttribute(PageData pd) throws Exception{
		if("true".equals(pd.getString("demoBoolean1"))){
			pd.put("demoBoolean1",true);
		}else{
			pd.put("demoBoolean1",false);
		}
		if("true".equals(pd.getString("demoBoolean2"))){
			pd.put("demoBoolean2",true);
		}else{
			pd.put("demoBoolean2",false);
		}
		return pd;
	}
}

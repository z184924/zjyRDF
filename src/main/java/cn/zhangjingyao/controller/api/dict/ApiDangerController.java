package cn.zhangjingyao.controller.api.dict;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.Page;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.service.dict.DangerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/** 
 * 类名称：ProjectController
 * 创建时间：2018-08-08
 */
@Controller
@RequestMapping(value="/api/danger")
public class ApiDangerController extends BaseController {

	@Resource
	private DangerService dangerService;
	
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
			this.dangerService.save(pd);
		}else {
			this.dangerService.edit(pd);
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
		this.dangerService.save(pd);
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
		dangerService.delete(pd);
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
		this.dangerService.edit(pd);
		return this.jsonContent("success", "保存成功");
	}
	
	/**
	 * 返回列表JSON
	 * @param page int,页码
	 * @param rows int,每页行数
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getGridListJson", produces = "application/json;charset=UTF-8")
	public Object getGridListJson() throws Exception {
		logBefore(logger, "获取Project列表Json");
		PageData pd = this.getPageData();
		PageInfo<PageData> pageInfo = this.dangerService.listPage(pd);// 分页查询列表
		return this.jsonContent("success",pageInfo);
	}
	/**
	 * 返回列表JSON
	 *
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/listAllContructionStageName", produces = "application/json;charset=UTF-8")
	public Object listAllContructionStageName() throws Exception {
		logBefore(logger, "listAllContructionStageName");
		PageData pd = this.getPageData();
		List<PageData> resultList = this.dangerService.listAllContructionStageName(pd);// 分页查询列表
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
		PageData resultPD = this.dangerService.findById(pd);
		return this.jsonContent("success",resultPD);
	}

	/**
	 * unitTypeCount
	 */
	@RequestMapping(value = "/unitTypeCount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object unitTypeCount() throws Exception {
		logBefore(logger, "unitTypeCount");
		PageData pd = this.getPageData();
		List<PageData> countList = this.dangerService.unitTypeCount(pd);
		List<String> xAxisData = new ArrayList<>();
		for (PageData countData:countList) {
			Boolean addFlag=true;
			for (String xAxis:xAxisData) {
				if(xAxis.equals(countData.getString("deptype"))){
					addFlag=false;
					break;
				}
			}
			if(addFlag){
				xAxisData.add(countData.getString("deptype"));
			}
		}
		List<String> legendData = new ArrayList<>();
		for (PageData countData:countList) {
			Boolean addFlag=true;
			for (String legend:legendData) {
				if(legend.equals(countData.getString("area"))){
					addFlag=false;
					break;
				}
			}
			if(addFlag){
				legendData.add(countData.getString("area"));
			}
		}
		List<PageData> series=new ArrayList<>();
		for (String legend:legendData) {
			PageData serie=new PageData();
			serie.put("name",legend);
			List<Integer> data=new ArrayList<>();
			for (String xAxis:xAxisData){
				for (PageData countData:countList) {
					if(legend.equals(countData.getString("area"))&&xAxis.equals(countData.getString("deptype"))){
						data.add(countData.getInt("counts"));
					}
				}
			}
			serie.put("data",data);
			series.add(serie);
		}
		PageData res=new PageData();
		res.put("xAxisData",xAxisData);
		res.put("legendData",legendData);
		res.put("series",series);
		return  this.jsonContent("success",res);
	}

	/**
	 * getTypePie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTypePie", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getTypePie() throws Exception {
		logBefore(logger, "getTypePie");
		PageData pd = this.getPageData();
		List<PageData> typePie = this.dangerService.getTypePie(pd);
		return  this.jsonContent("success",typePie);
	}

	/**
	 * gradePartition
	 */
	@RequestMapping(value = "/gradePartition", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object gradePartition() throws Exception {
		logBefore(logger, "gradePartition");
		PageData pd = this.getPageData();
		List<PageData> countList = this.dangerService.gradePartition(pd);
		List<PageData> areaCountList = this.dangerService.gradePartitionAreaCount(pd);
		List<String> yAxisData = new ArrayList<>();
		for (PageData countData:countList) {
			Boolean addFlag=true;
			for (String yAxis:yAxisData) {
				if(yAxis.equals(countData.getString("area"))){
					addFlag=false;
					break;
				}
			}
			if(addFlag){
				yAxisData.add(countData.getString("area"));
			}
		}
		List<String> legendData = new ArrayList<>();
		for (PageData countData:countList) {
			Boolean addFlag=true;
			for (String legend:legendData) {
				if(legend.equals(countData.getString("level"))){
					addFlag=false;
					break;
				}
			}
			if(addFlag){
				legendData.add(countData.getString("level"));
			}
		}
		legendData.add("总计");
		List<PageData> series=new ArrayList<>();
		for (String legend:legendData) {
			PageData serie=new PageData();
			serie.put("name",legend);
			List<Integer> data=new ArrayList<>();
			for (String yAxis:yAxisData){
				for (PageData countData:countList) {
					if(legend.equals(countData.getString("level"))&&yAxis.equals(countData.getString("area"))){
						data.add(countData.getInt("counts"));
					}
				}
				for(PageData areaCountData:areaCountList){
					if("总计".equals(legend) &&yAxis.equals(areaCountData.getString("area"))){
						data.add(areaCountData.getInt("counts"));
					}
				}
			}
			serie.put("data",data);
			series.add(serie);
		}
		PageData res=new PageData();
		res.put("yAxisData",yAxisData);
		res.put("legendData",legendData);
		res.put("series",series);
		return  this.jsonContent("success",res);
	}
	/**
	 * recordgetCountTime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/recordgetCountTime", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object recordgetCountTime() throws Exception {
		logBefore(logger, "recordgetCountTime");
		PageData pd = this.getPageData();
		List<PageData> recordgetCountTime = this.dangerService.recordgetCountTime(pd);
		return  this.jsonContent("success",recordgetCountTime);
	}
	
}

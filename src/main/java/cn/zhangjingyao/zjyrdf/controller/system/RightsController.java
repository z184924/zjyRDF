package cn.zhangjingyao.zjyrdf.controller.system;

import cn.zhangjingyao.zjyrdf.annotation.SystemLog;
import cn.zhangjingyao.zjyrdf.controller.base.BaseController;
import cn.zhangjingyao.zjyrdf.entity.PageData;
import cn.zhangjingyao.zjyrdf.service.system.RightsService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 类名称：RightsController
 * 创建时间：2019-03-05
 *
 * @author
 */
@Controller
@RequestMapping(value = "/rights")
public class RightsController extends BaseController {

    @Resource(name = "rightsService")
    private RightsService rightsService;

    /**
     * 新增或编辑
     */
    @SystemLog("新增或编辑Rights")
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
    public String saveOrUpdate() {
        PageData pd = this.getPageData();
        if (pd.get("rightsId") == null || "".equals(pd.get("rightsId")) || 0 == pd.getInt("rightsId")) {
            //替换字段
            pd = this.replaceAttribute(pd);
            this.rightsService.save(pd);
        } else {
            //替换字段
            pd = this.replaceAttribute(pd);
            this.rightsService.edit(pd);
        }
        return this.jsonContent("success", "保存成功");
    }

    /**
     * 新增
     */
    @SystemLog("新增Rights")
    @ResponseBody
    @RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
    public String save() {
        PageData pd = this.getPageData();
        //替换字段
        pd = this.replaceAttribute(pd);
        this.rightsService.save(pd);
        return this.jsonContent("success", "保存成功");
    }

    /**
     * 删除
     */
    @SystemLog("删除Rights")
    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    public String delete() {
        PageData pd = this.getPageData();
        rightsService.delete(pd);
        return this.jsonContent("success", "删除成功");
    }

    /**
     * 修改
     */
    @SystemLog("修改Rights")
    @ResponseBody
    @RequestMapping(value = "/edit", produces = "application/json;charset=UTF-8")
    public String edit() {
        PageData pd = this.getPageData();
        //替换字段
        pd = this.replaceAttribute(pd);
        this.rightsService.edit(pd);
        return this.jsonContent("success", "保存成功");
    }

    /**
     * 分页查询列表
     */
    @SystemLog("分页查询Rights列表")
    @ResponseBody
    @RequestMapping(value = "/listPage", produces = "application/json;charset=UTF-8")
    public Object listPage() {
        PageData pd = this.getPageData();
        // 分页查询列表
        PageInfo<PageData> pageInfo = this.rightsService.listPage(pd);
        return this.jsonContent("success", pageInfo);
    }

    /**
     * 根据ID查询单条数据
     */
    @SystemLog("根据ID查询Rights数据")
    @ResponseBody
    @RequestMapping(value = "/findById", produces = "application/json;charset=UTF-8")
    public Object findById() {
        PageData pd = this.getPageData();
        PageData resultPD = this.rightsService.findById(pd);
        return this.jsonContent("success", resultPD);
    }

    /**
     * 替换字段
     *
     * @param pd
     * @return 替换后PageData
     */
    private PageData replaceAttribute(PageData pd) {
        return pd;
    }
}

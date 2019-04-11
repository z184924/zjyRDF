package cn.zhangjingyao.controller.system;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import cn.zhangjingyao.service.system.RightsService;
import cn.zhangjingyao.service.system.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.zhangjingyao.service.system.RoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：RoleController
 * 创建时间：2019-03-05
 *
 * @author
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Resource(name = "roleService")
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RightsService rightsService;

    /**
     * 新增或编辑
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
    public String saveOrUpdate() throws Exception {
        logger.info("新增或编辑Role");
        PageData pd = this.getPageData();
        if (pd.get("roleId") == null || "".equals(pd.get("roleId"))) {
            //添加主键
            pd.put("roleId", this.get32UUID());
            //替换字段
            pd = this.replaceAttribute(pd);
            this.roleService.save(pd);
        } else {
            //替换字段
            pd = this.replaceAttribute(pd);
            this.roleService.edit(pd);
        }
        return this.jsonContent("success", "保存成功");
    }

    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
    public String save() throws Exception {
        logger.info("新增Role");
        PageData pd = this.getPageData();
        //添加主键
        pd.put("roleId", this.get32UUID());
        //替换字段
        pd = this.replaceAttribute(pd);
        this.roleService.save(pd);
        return this.jsonContent("success", "保存成功");
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    public String delete() throws Exception {
        logger.info("删除Role");
        PageData pd = this.getPageData();
        roleService.delete(pd);
        return this.jsonContent("success", "删除成功");
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/edit", produces = "application/json;charset=UTF-8")
    public String edit() throws Exception {
        logger.info("修改Role");
        PageData pd = this.getPageData();
        //替换字段
        pd = this.replaceAttribute(pd);
        this.roleService.edit(pd);
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
        logger.info("获取Role列表Json");
        PageData pd = this.getPageData();
        // 分页查询列表
        PageInfo<PageData> pageInfo = this.roleService.listPage(pd);
        return this.jsonContent("success", pageInfo);
    }

    /**
     * 根据ID查询单条数据
     */
    @RequestMapping(value = "/findById", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findById() throws Exception {
        logger.info("根据ID获取Role数据");
        PageData pd = this.getPageData();
        PageData resultPD = this.roleService.findById(pd);
        return this.jsonContent("success", resultPD);
    }

    /**
     * 列表用户角色关系
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/listUserRole", produces = "application/json;charset=UTF-8")
    public Object listUserRole() throws Exception {
        logger.info("获取用户角色关系列表Json");
        PageData pd = this.getPageData();
        List<PageData> userRoleList = this.roleService.listUserRole(pd);
        List<PageData> userList = this.userService.listAll(pd);
        PageData result = new PageData();
        result.put("userRoleList", userRoleList);
        result.put("userList", userList);
        return this.jsonContent("success", result);
    }

    /**
     * 编辑用户角色关系
     */
    @ResponseBody
    @RequestMapping(value = "/editUserRole", produces = "application/json;charset=UTF-8")
    public String editUserRole() throws Exception {
        logger.info("编辑用户角色关系");
        PageData pd = this.getPageData();
        String roleId = pd.getString("roleId");
        String checkedKeysString = pd.getString("checkedKeys");
        String[] checkedKeys = checkedKeysString.split(",");
        List<PageData> list = new ArrayList<>();
        for (String userId : checkedKeys) {
            if (userId.isEmpty()) {
                continue;
            }
            PageData relation = new PageData();
            relation.put("id", this.get32UUID());
            relation.put("userId", userId);
            relation.put("roleId", roleId);
            list.add(relation);
        }
        this.roleService.editUserRole(roleId, list);
        return this.jsonContent("success", "保存成功");
    }

    /**
     * 列表角色权限关系
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/listRoleRights", produces = "application/json;charset=UTF-8")
    public Object listRoleRights() throws Exception {
        logger.info("获取角色权限关系列表Json");
        PageData pd = this.getPageData();
        List<PageData> roleRightsList = this.roleService.listRoleRights(pd);
        List<PageData> rightsList = this.rightsService.listAll(pd);
        PageData result = new PageData();
        result.put("roleRightsList", roleRightsList);
        result.put("rightsList", rightsList);
        return this.jsonContent("success", result);
    }

    /**
     * 编辑角色权限关系
     */
    @ResponseBody
    @RequestMapping(value = "/editRoleRights", produces = "application/json;charset=UTF-8")
    public String editRoleRights() throws Exception {
        logger.info("编辑角色权限关系");
        PageData pd = this.getPageData();
        String roleId = pd.getString("roleId");
        String checkedKeysString = pd.getString("checkedKeys");
        String[] checkedKeys = checkedKeysString.split(",");
        List<PageData> list = new ArrayList<>();
        for (String rightsId : checkedKeys) {
            if (rightsId.isEmpty()) {
                continue;
            }
            PageData relation = new PageData();
            relation.put("id", this.get32UUID());
            relation.put("rightsId", rightsId);
            relation.put("roleId", roleId);
            list.add(relation);
        }
        this.roleService.editRoleRights(roleId, list);
        return this.jsonContent("success", "保存成功");
    }

    /**
     * 查询用户权限关系
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/listUserRights", produces = "application/json;charset=UTF-8")
    public Object listUserRights() throws Exception {
        logger.info("获取用户权限关系列表Json");
        User currentUser = this.getCurrentUser();
        PageData pd = new PageData();
        pd.put("userId", this.getCurrentUser().getUserId());
        List<PageData> userRightsList = this.roleService.listUserRights(pd);
        return this.jsonContent("success", userRightsList);
    }

    /**
     * 替换字段
     *
     * @param pd
     * @return 替换后PageData
     * @throws Exception
     */
    private PageData replaceAttribute(PageData pd) throws Exception {
        return pd;
    }
}

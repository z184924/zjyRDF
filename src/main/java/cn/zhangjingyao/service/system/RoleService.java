package cn.zhangjingyao.service.system;

import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author
 */
@org.springframework.stereotype.Service
public class RoleService extends BaseService {

    /**
     * 新增
     *
     * @param pd
     */
    public void save(PageData pd) {
        dao.save("RoleMapper.save", pd);
    }

    /**
     * 批量新增
     *
     * @param list
     */
    public void save(List<PageData> list) {
        for (PageData pd : list) {
            dao.save("RoleMapper.save", pd);
        }
    }

    /**
     * 删除
     *
     * @param pd
     */
    public void delete(PageData pd) {
        dao.delete("RoleMapper.deleteUserRole", pd.getString("roleId"));
        dao.delete("RoleMapper.deleteRoleRights", pd.getString("roleId"));
        dao.delete("RoleMapper.delete", pd);
    }

    /**
     * 批量删除
     *
     * @param list
     */
    public void delete(List<PageData> list) {
        for (PageData pd : list) {
            dao.delete("RoleMapper.deleteUserRole", pd.getString("roleId"));
            dao.delete("RoleMapper.deleteRoleRights", pd.getString("roleId"));
            dao.delete("RoleMapper.delete", pd);
        }
    }

    /**
     * 修改
     *
     * @param pd
     */
    public void edit(PageData pd) {
        dao.update("RoleMapper.edit", pd);
    }

    /**
     * 批量修改
     *
     * @param list
     */
    public void edit(List<PageData> list) {
        for (PageData pd : list) {
            dao.update("RoleMapper.edit", pd);
        }
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    public PageInfo<PageData> listPage(PageData pd) {
        PageHelper.startPage(pd.getInt("pageNum"), pd.getInt("pageSize"));
        List<PageData> list = (List<PageData>) dao.findForList("RoleMapper.listAll", pd);
        return new PageInfo(list);
    }

    /**
     * 查询(全部)
     *
     * @param pd
     * @return
     */
    public List<PageData> listAll(PageData pd) {
        return (List<PageData>) dao.findForList("RoleMapper.listAll", pd);
    }

    /**
     * 通过id获取数据
     *
     * @param pd
     * @return
     */
    public PageData findById(PageData pd) {
        return (PageData) dao.findForObject("RoleMapper.findById", pd);
    }

    /**
     * 批量删除
     *
     * @param arrayDataIds
     */
    public void deleteAll(String[] arrayDataIds) {
        dao.delete("RoleMapper.deleteAll", arrayDataIds);
    }

    /**
     * 查询用户角色关系
     *
     * @param pd
     * @return
     */
    public List<PageData> listUserRole(PageData pd) {
        return (List<PageData>) dao.findForList("RoleMapper.listUserRole", pd);
    }

    /**
     * 编辑用户角色关系
     */
    public void editUserRole(String roleId, List<PageData> list) {
        dao.delete("RoleMapper.deleteUserRole", roleId);
        for (PageData pd : list) {
            dao.save("RoleMapper.saveUserRole", pd);
        }
    }

    /**
     * 查询角色权限关系
     *
     * @param pd
     * @return
     */
    public List<PageData> listRoleRights(PageData pd) {
        return (List<PageData>) dao.findForList("RoleMapper.listRoleRights", pd);
    }

    /**
     * 编辑角色权限关系
     */
    public void editRoleRights(String roleId, List<PageData> list) {
        dao.delete("RoleMapper.deleteRoleRights", roleId);
        for (PageData pd : list) {
            dao.save("RoleMapper.saveRoleRights", pd);
        }
    }

    /**
     * 查询用户权限关系
     *
     * @param pd
     * @return
     */
    public List<PageData> listUserRights(PageData pd) {
        return (List<PageData>) dao.findForList("RoleMapper.listUserRights", pd);
    }

    /**
     * 查询用户权限关系
     *
     * @param pd
     * @return
     */
    public List<PageData> listUserRights(String userId) {
        PageData pd = new PageData();
        pd.put("userId", userId);
        return (List<PageData>) dao.findForList("RoleMapper.listUserRights", pd);
    }
}


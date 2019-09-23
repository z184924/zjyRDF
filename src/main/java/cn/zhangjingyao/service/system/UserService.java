package cn.zhangjingyao.service.system;

import cn.zhangjingyao.dao.DaoImpl;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;


/**
 * @author
 */
@org.springframework.stereotype.Service
public class UserService {

    @Autowired
    private DaoImpl dao;

    /**
     * 通过用户名密码获取数据
     */
    public User loginUser(PageData pd) {
        return (User) dao.findForObject("UserMapper.loginUser", pd);
    }

    /**
     * 新增
     *
     * @param pd
     */
    public boolean save(PageData pd) {
        PageData account = (PageData) dao.findForObject("UserMapper.findByAccount", pd);
        if (account == null) {
            dao.save("UserMapper.save", pd);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 批量新增
     *
     * @param list
     */
    public Boolean save(List<PageData> list) {
        Boolean saveFlag = true;
        for (PageData pd : list) {
            PageData account = (PageData) dao.findForObject("UserMapper.findByAccount", pd);
            if (account == null) {
                dao.save("UserMapper.save", pd);
            } else {
                saveFlag = false;
                break;
            }
        }
        if (!saveFlag) {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return saveFlag;
    }

    /**
     * 删除
     *
     * @param pd
     */
    public void delete(PageData pd) {
        dao.delete("UserMapper.delete", pd);
    }

    /**
     * 批量删除
     *
     * @param list
     */
    public void delete(List<PageData> list) {
        for (PageData pd : list) {
            dao.delete("UserMapper.delete", pd);
        }
    }

    /**
     * 修改
     *
     * @param pd
     */
    public void edit(PageData pd) {
        dao.update("UserMapper.edit", pd);
    }

    /**
     * 批量修改
     *
     * @param list
     */
    public void edit(List<PageData> list) {
        for (PageData pd : list) {
            dao.update("UserMapper.edit", pd);
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
        List<PageData> list = (List<PageData>) dao.findForList("UserMapper.listAll", pd);
        return new PageInfo(list);
    }

    /**
     * 查询(全部)
     *
     * @param pd
     * @return
     */
    public List<PageData> listAll(PageData pd) {
        return (List<PageData>) dao.findForList("UserMapper.listAll", pd);
    }

    /**
     * 通过id获取数据
     *
     * @param pd
     * @return
     */
    public PageData findById(PageData pd) {
        return (PageData) dao.findForObject("UserMapper.findById", pd);
    }

    /**
     * 通过id获取数据
     *
     * @param pd
     * @return
     */
    public PageData findById(String userId) {
        PageData pd = new PageData();
        pd.put("userId", userId);
        return (PageData) dao.findForObject("UserMapper.findById", pd);
    }

    /**
     * 通过account获取数据
     *
     * @param pd
     * @return
     */
    public PageData findByAccount(PageData pd) {
        return (PageData) dao.findForObject("UserMapper.findByAccount", pd);
    }

    /**
     * 通过account获取数据
     *
     * @param pd
     * @return
     */
    public PageData findByAccount(String account) {
        PageData pd = new PageData();
        pd.put("account", account);
        return (PageData) dao.findForObject("UserMapper.findByAccount", pd);
    }

    /**
     * 批量删除
     *
     * @param arrayDataIds
     */
    public void deleteAll(String[] arrayDataIds) {
        dao.delete("UserMapper.deleteAll", arrayDataIds);
    }

}


package cn.zhangjingyao.zjyrdf.service.system;

import cn.zhangjingyao.zjyrdf.entity.PageData;
import cn.zhangjingyao.zjyrdf.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author
 */
@org.springframework.stereotype.Service
public class RightsService extends BaseService {

    /**
     * 新增
     *
     * @param pd
     */
    public void save(PageData pd) {
        dao.save("RightsMapper.save", pd);
    }

    /**
     * 批量新增
     *
     * @param list
     */
    public void save(List<PageData> list) {
        for (PageData pd : list) {
            dao.save("RightsMapper.save", pd);
        }
    }

    /**
     * 删除
     *
     * @param pd
     */
    public void delete(PageData pd) {
        dao.delete("RightsMapper.delete", pd);
    }

    /**
     * 批量删除
     *
     * @param list
     */
    public void delete(List<PageData> list) {
        for (PageData pd : list) {
            dao.delete("RightsMapper.delete", pd);
        }
    }

    /**
     * 修改
     *
     * @param pd
     */
    public void edit(PageData pd) {
        dao.update("RightsMapper.edit", pd);
    }

    /**
     * 批量修改
     *
     * @param list
     */
    public void edit(List<PageData> list) {
        for (PageData pd : list) {
            dao.update("RightsMapper.edit", pd);
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
        List<PageData> list = (List<PageData>) dao.findForList("RightsMapper.listAll", pd);
        return new PageInfo(list);
    }

    /**
     * 查询(全部)
     *
     * @param pd
     * @return
     */
    public List<PageData> listAll(PageData pd) {
        return (List<PageData>) dao.findForList("RightsMapper.listAll", pd);
    }

    /**
     * 通过id获取数据
     *
     * @param pd
     * @return
     */
    public PageData findById(PageData pd) {
        return (PageData) dao.findForObject("RightsMapper.findById", pd);
    }

    /**
     * 批量删除
     *
     * @param arrayDataIds
     */
    public void deleteAll(String[] arrayDataIds) {
        dao.delete("RightsMapper.deleteAll", arrayDataIds);
    }

}


package cn.zhangjingyao.zjyrdf.service.log;

import cn.zhangjingyao.zjyrdf.entity.PageData;
import cn.zhangjingyao.zjyrdf.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 类名称:LogIndexService
 * 创建时间:2019-12-19
 *
 * @author CodeGenerator
 */
@org.springframework.stereotype.Service
public class LogIndexService extends BaseService {

    /**
     * 新增LogIndex
     *
     * @param pd PageData
     */
    public void save(PageData pd) {
        dao.save("LogIndexMapper.save", pd);
    }

    /**
     * 批量新增LogIndex
     *
     * @param list PageData List
     */
    public void save(List<PageData> list) {
        for (PageData pd : list) {
            dao.save("LogIndexMapper.save", pd);
        }
    }

    /**
     * 删除LogIndex
     *
     * @param pd PageData
     */
    public void delete(PageData pd) {
        dao.delete("LogIndexMapper.delete", pd);
    }

    /**
     * 批量删除LogIndex
     *
     * @param list PageData List
     */
    public void delete(List<PageData> list) {
        for (PageData pd : list) {
            dao.delete("LogIndexMapper.delete", pd);
        }
    }

    /**
     * 修改LogIndex
     *
     * @param pd PageData
     */
    public void edit(PageData pd) {
        dao.update("LogIndexMapper.edit", pd);
    }

    /**
     * 批量修改LogIndex
     *
     * @param list PageData List
     */
    public void edit(List<PageData> list) {
        for (PageData pd : list) {
            dao.update("LogIndexMapper.edit", pd);
        }
    }

    /**
     * 分页查询LogIndex
     *
     * @param pd PageData
     * @return PageInfo
     */
    public PageInfo<PageData> listPage(PageData pd) {
        PageHelper.startPage(pd.getInt("pageNum"), pd.getInt("pageSize"));
        List<PageData> list = (List<PageData>) dao.findForList("LogIndexMapper.listAll", pd);
        return new PageInfo(list);
    }

    /**
     * 查询LogIndex(全部)
     *
     * @param pd PageData
     * @return PageData List
     */
    public List<PageData> listAll(PageData pd) {
        return (List<PageData>) dao.findForList("LogIndexMapper.listAll", pd);
    }

    /**
     * 通过id获取LogIndex数据
     *
     * @param pd PageData
     * @return PageData
     */
    public PageData findById(PageData pd) {
        return (PageData) dao.findForObject("LogIndexMapper.findById", pd);
    }

    /**
     * 批量删除LogIndex
     *
     * @param arrayDataIds Id数组
     */
    public void deleteAll(String[] arrayDataIds) {
        dao.delete("LogIndexMapper.deleteAll", arrayDataIds);
    }

}


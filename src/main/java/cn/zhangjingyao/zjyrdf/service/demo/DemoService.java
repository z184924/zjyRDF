package cn.zhangjingyao.zjyrdf.service.demo;

import cn.zhangjingyao.zjyrdf.entity.PageData;
import cn.zhangjingyao.zjyrdf.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 类名称:DemoService
 * 创建时间:2019-12-03
 *
 * @author CodeGenerator
 */
@org.springframework.stereotype.Service
public class DemoService extends BaseService {
	
	/**
	 * 新增
     *
	 * @param pd PageData
	 */
	public void save(PageData pd) {
		dao.save("DemoMapper.save", pd);
	}

	/**
	 * 批量新增
     *
	 * @param list PageData List
	 */
	public void save(List<PageData> list) {
		for (PageData pd : list) {
			dao.save("DemoMapper.save", pd);
		}
	}

    /**
     * 删除
     *
     * @param pd PageData
     */
	public void delete(PageData pd) {
		dao.delete("DemoMapper.delete", pd);
	}

    /**
     * 批量删除
	 *
     * @param list PageData List
     */
	public void delete(List<PageData> list) {
        for (PageData pd : list) {
			dao.delete("DemoMapper.delete", pd);
		}
	}

    /**
     * 修改
     *
     * @param pd PageData
     */
	public void edit(PageData pd) {
		dao.update("DemoMapper.edit", pd);
	}

	/**
	 * 批量修改
     *
     * @param list PageData List
     */
	public void edit(List<PageData> list) {
        for (PageData pd : list) {
			dao.update("DemoMapper.edit", pd);
		}
	}

    /**
	 * 分页查询
     *
     * @param pd PageData
     * @return PageInfo
     */
	public PageInfo<PageData> listPage(PageData pd) {
        PageHelper.startPage(pd.getInt("pageNum"), pd.getInt("pageSize"));
        List<PageData> list = (List<PageData>) dao.findForList("DemoMapper.listAll", pd);
        return new PageInfo(list);
	}

    /**
     * 查询(全部)
     *
     * @param pd PageData
     * @return PageData List
     */
	public List<PageData> listAll(PageData pd) {
		return (List<PageData>) dao.findForList("DemoMapper.listAll", pd);
	}

    /**
     * 通过id获取数据
     *
     * @param pd PageData
     * @return PageData
     */
	public PageData findById(PageData pd) {
		return (PageData) dao.findForObject("DemoMapper.findById", pd);
	}

    /**
     * 批量删除
     *
     * @param arrayDataIds Id数组
     */
	public void deleteAll(String[] arrayDataIds) {
		dao.delete("DemoMapper.deleteAll", arrayDataIds);
	}
	
}


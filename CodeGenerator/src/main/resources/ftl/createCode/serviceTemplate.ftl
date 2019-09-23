package cn.zhangjingyao.service.${packageName};

import cn.zhangjingyao.dao.DaoImpl;
import cn.zhangjingyao.entity.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 类名称:${objectName}Service
 * 创建时间:${nowDate?string("yyyy-MM-dd")}
 *
 * @author
 */
@org.springframework.stereotype.Service
public class ${objectName}Service {

	@Autowired
	private DaoImpl dao;
	
	/**
	 * 新增
     *
	 * @param pd PageData
	 */
	public void save(PageData pd) {
		dao.save("${objectName}Mapper.save", pd);
	}

	/**
	 * 批量新增
     *
	 * @param list PageData List
	 */
	public void save(List<PageData> list) {
		for (PageData pd : list) {
			dao.save("${objectName}Mapper.save", pd);
		}
	}

    /**
     * 删除
     *
     * @param pd PageData
     */
	public void delete(PageData pd) {
		dao.delete("${objectName}Mapper.delete", pd);
	}

    /**
     * 批量删除
	 *
     * @param list PageData List
     */
	public void delete(List<PageData> list) {
        for (PageData pd : list) {
			dao.delete("${objectName}Mapper.delete", pd);
		}
	}

    /**
     * 修改
     *
     * @param pd PageData
     */
	public void edit(PageData pd) {
		dao.update("${objectName}Mapper.edit", pd);
	}

	/**
	 * 批量修改
     *
     * @param list PageData List
     */
	public void edit(List<PageData> list) {
        for (PageData pd : list) {
			dao.update("${objectName}Mapper.edit", pd);
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
        List<PageData> list = (List<PageData>) dao.findForList("${objectName}Mapper.listAll", pd);
        return new PageInfo(list);
	}

    /**
     * 查询(全部)
     *
     * @param pd PageData
     * @return PageData List
     */
	public List<PageData> listAll(PageData pd) {
		return (List<PageData>) dao.findForList("${objectName}Mapper.listAll", pd);
	}

    /**
     * 通过id获取数据
     *
     * @param pd PageData
     * @return PageData
     */
	public PageData findById(PageData pd) {
		return (PageData) dao.findForObject("${objectName}Mapper.findById", pd);
	}

    /**
     * 批量删除
     *
     * @param arrayDataIds Id数组
     */
	public void deleteAll(String[] arrayDataIds) {
		dao.delete("${objectName}Mapper.deleteAll", arrayDataIds);
	}
	
}


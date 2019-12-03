package cn.zhangjingyao.service.${packageName};

import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 类名称:${className}Service
 * 创建时间:${.now?string("yyyy-MM-dd")}
 *
 * @author CodeGenerator
 */
@org.springframework.stereotype.Service
public class ${className}Service extends BaseService {
	
	/**
	 * 新增${className}
     *
	 * @param pd PageData
	 */
	public void save(PageData pd) {
		dao.save("${className}Mapper.save", pd);
	}

	/**
	 * 批量新增${className}
     *
	 * @param list PageData List
	 */
	public void save(List<PageData> list) {
		for (PageData pd : list) {
			dao.save("${className}Mapper.save", pd);
		}
	}

    /**
     * 删除${className}
     *
     * @param pd PageData
     */
	public void delete(PageData pd) {
		dao.delete("${className}Mapper.delete", pd);
	}

    /**
     * 批量删除${className}
	 *
     * @param list PageData List
     */
	public void delete(List<PageData> list) {
        for (PageData pd : list) {
			dao.delete("${className}Mapper.delete", pd);
		}
	}

    /**
     * 修改${className}
     *
     * @param pd PageData
     */
	public void edit(PageData pd) {
		dao.update("${className}Mapper.edit", pd);
	}

	/**
	 * 批量修改${className}
     *
     * @param list PageData List
     */
	public void edit(List<PageData> list) {
        for (PageData pd : list) {
			dao.update("${className}Mapper.edit", pd);
		}
	}

    /**
	 * 分页查询${className}
     *
     * @param pd PageData
     * @return PageInfo
     */
	public PageInfo<PageData> listPage(PageData pd) {
        PageHelper.startPage(pd.getInt("pageNum"), pd.getInt("pageSize"));
        List<PageData> list = (List<PageData>) dao.findForList("${className}Mapper.listAll", pd);
        return new PageInfo(list);
	}

    /**
     * 查询${className}(全部)
     *
     * @param pd PageData
     * @return PageData List
     */
	public List<PageData> listAll(PageData pd) {
		return (List<PageData>) dao.findForList("${className}Mapper.listAll", pd);
	}

    /**
     * 通过id获取${className}数据
     *
     * @param pd PageData
     * @return PageData
     */
	public PageData findById(PageData pd) {
		return (PageData) dao.findForObject("${className}Mapper.findById", pd);
	}

    /**
     * 批量删除${className}
     *
     * @param arrayDataIds Id数组
     */
	public void deleteAll(String[] arrayDataIds) {
		dao.delete("${className}Mapper.deleteAll", arrayDataIds);
	}
	
}


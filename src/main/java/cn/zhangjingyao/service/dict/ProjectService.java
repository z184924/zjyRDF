package cn.zhangjingyao.service.dict;

import cn.zhangjingyao.dao.DaoImpl;
import cn.zhangjingyao.entity.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ProjectService {

	@Autowired
	private DaoImpl dao;
	
	/**
	 * 新增
	 * @param pd
	 * @throws Exception
	 */

	public void save(PageData pd)throws Exception{
		dao.save("ProjectMapper.save", pd);
	}

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */

	public void save(List<PageData> list)throws Exception{
		for (PageData pd:list) {
			dao.save("ProjectMapper.save", pd);
		}
	}

    /**
     * 删除
     * @param pd
     * @throws Exception
     */

	public void delete(PageData pd)throws Exception{
		dao.delete("ProjectMapper.delete", pd);
	}

    /**
     * 批量删除
     * @param list
     * @throws Exception
     */

	public void delete(List<PageData> list)throws Exception{
        for (PageData pd:list) {
			dao.delete("ProjectMapper.delete", pd);
		}
	}

    /**
     * 修改
     * @param pd
     * @throws Exception
     */

	public void edit(PageData pd)throws Exception{
		dao.update("ProjectMapper.edit", pd);
	}

	/**
	 * 批量修改
     * @param list
     * @throws Exception
     */

	public void edit(List<PageData> list)throws Exception{
        for (PageData pd:list) {
			dao.update("ProjectMapper.edit", pd);
		}
	}

	/**
	 * 列表
	 * @param page
	 * @return
	 * @throws Exception
	 */

	public PageInfo<PageData> listPage(PageData pd)throws Exception{
		PageHelper.startPage(pd.getInt("page"),pd.getInt("rows"));
		List<PageData> forList = (List<PageData>) dao.findForList("ProjectMapper.listAll", pd);
		return new PageInfo(forList);
	}

    /**
     * 列表(全部)
     * @param pd
     * @return
     * @throws Exception
     */

	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ProjectMapper.listAll", pd);
	}
    /**
     * 列表(全部)
     * @param pd
     * @return
     * @throws Exception
     */

	public List<PageData> listAllCity(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ProjectMapper.listAllCity", pd);
	}

     /**
      * 通过id获取数据
      * @param pd
      * @return
      * @throws Exception
      */

	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ProjectMapper.findById", pd);
	}

     /**
      * 批量删除
      * @param ArrayDATA_IDS
      * @throws Exception
      */

	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ProjectMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


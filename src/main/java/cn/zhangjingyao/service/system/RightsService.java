package cn.zhangjingyao.service.system;

import cn.zhangjingyao.dao.DaoImpl;
import cn.zhangjingyao.entity.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author
 */
@org.springframework.stereotype.Service
public class RightsService {

	@Autowired
	private DaoImpl dao;
	
	/**
	 * 新增
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void save(PageData pd)throws Exception{
		dao.save("RightsMapper.save", pd);
	}

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void save(List<PageData> list)throws Exception{
		for (PageData pd:list) {
			dao.save("RightsMapper.save", pd);
		}
	}

    /**
     * 删除
     * @param pd
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(PageData pd)throws Exception{
		dao.delete("RightsMapper.delete", pd);
	}

    /**
     * 批量删除
     * @param list
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(List<PageData> list)throws Exception{
        for (PageData pd:list) {
			dao.delete("RightsMapper.delete", pd);
		}
	}

    /**
     * 修改
     * @param pd
     * @throws Exception
     */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void edit(PageData pd)throws Exception{
		dao.update("RightsMapper.edit", pd);
	}

	/**
	 * 批量修改
     * @param list
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void edit(List<PageData> list)throws Exception{
        for (PageData pd:list) {
			dao.update("RightsMapper.edit", pd);
		}
	}

    /**
	 * 分页查询
     * @param page
     * @return
     * @throws Exception
     */
	public PageInfo<PageData> listPage(PageData pd)throws Exception{
        PageHelper.startPage(pd.getInt("pageNum"),pd.getInt("pageSize"));
        List<PageData> list = (List<PageData>) dao.findForList("RightsMapper.listAll", pd);
        return new PageInfo(list);
	}

    /**
     * 查询(全部)
     * @param pd
     * @return
     * @throws Exception
     */
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("RightsMapper.listAll", pd);
	}

     /**
      * 通过id获取数据
      * @param pd
      * @return
      * @throws Exception
      */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("RightsMapper.findById", pd);
	}

     /**
      * 批量删除
      * @param arrayDataIds
      * @throws Exception
      */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteAll(String[] arrayDataIds)throws Exception{
		dao.delete("RightsMapper.deleteAll", arrayDataIds);
	}
	
}


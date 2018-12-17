package cn.zhangjingyao.service.dict;

import cn.zhangjingyao.dao.DaoImpl;
import cn.zhangjingyao.entity.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@org.springframework.stereotype.Service
public class DangerService {

	@Autowired
	private DaoImpl dao;

	/**
	 * 新增
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void save(PageData pd)throws Exception{
		dao.save("DangerMapper.save", pd);
	}

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void save(List<PageData> list)throws Exception{
		for (PageData pd:list) {
			dao.save("DangerMapper.save", pd);
		}
	}

	/**
	 * 删除
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(PageData pd)throws Exception{
		dao.delete("DangerMapper.delete", pd);
	}

	/**
	 * 批量删除
	 * @param list
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(List<PageData> list)throws Exception{
        for (PageData pd:list) {
			dao.delete("DangerMapper.delete", pd);
		}
	}

	/**
	 * 修改
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void edit(PageData pd)throws Exception{
		dao.update("DangerMapper.edit", pd);
	}

	/**
	 * 批量修改
	 * @param list
	 * @throws Exception
	 */

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void edit(List<PageData> list)throws Exception{
        for (PageData pd:list) {
			dao.update("DangerMapper.edit", pd);
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
		List<PageData> forList = (List<PageData>) dao.findForList("DangerMapper.listAll", pd);
		return new PageInfo(forList);
	}

	/**
	 * 列表(全部)
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DangerMapper.listAll", pd);
	}
	/**
	 * 列表(全部)
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public List<PageData> listAllContructionStageName(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DangerMapper.listAllContructionStageName", pd);
	}



	/**
	 * 查询最后隐患时间
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public PageData findLastUploadTime(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DangerMapper.findLastUploadTime", pd);
	}

	/**
	 * unitTypeCount
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public List<PageData> unitTypeCount(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DangerMapper.unitTypeCount", pd);
	}
	/**
	 * getTypePie
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public List<PageData> getTypePie(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DangerMapper.getTypePie", pd);
	}
	/**
	 * gradePartition
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public List<PageData> gradePartition(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DangerMapper.gradePartition", pd);
	}
	/**
	 * gradePartitionAreaCount
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public List<PageData> gradePartitionAreaCount(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DangerMapper.gradePartitionAreaCount", pd);
	}
	/**
	 * recordgetCountTime
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public List<PageData> recordgetCountTime(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DangerMapper.recordgetCountTime", pd);
	}


	/**
	 * 通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */

	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DangerMapper.findById", pd);
	}

	/**
	 * 批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("DangerMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


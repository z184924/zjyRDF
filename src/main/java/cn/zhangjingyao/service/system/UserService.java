package cn.zhangjingyao.service.system;

import cn.zhangjingyao.dao.DaoImpl;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@org.springframework.stereotype.Service
public class UserService {
	
	@Autowired
	private DaoImpl dao;

	/*
	 * 通过用户名密码获取数据
	 */

	public User loginUser(PageData pd)throws Exception{
		return (User)dao.findForObject("UserMapper.loginUser", pd);
	}

	/**
	 * 新增
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void save(PageData pd)throws Exception{
		dao.save("UserMapper.save", pd);
	}

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void save(List<PageData> list)throws Exception{
		for (PageData pd:list) {
			dao.save("UserMapper.save", pd);
		}
	}

	/**
	 * 删除
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(PageData pd)throws Exception{
		dao.delete("UserMapper.delete", pd);
	}

	/**
	 * 批量删除
	 * @param list
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(List<PageData> list)throws Exception{
		for (PageData pd:list) {
			dao.delete("UserMapper.delete", pd);
		}
	}

	/**
	 * 修改
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void edit(PageData pd)throws Exception{
		dao.update("UserMapper.edit", pd);
	}

	/**
	 * 批量修改
	 * @param list
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void edit(List<PageData> list)throws Exception{
		for (PageData pd:list) {
			dao.update("UserMapper.edit", pd);
		}
	}

	/**
	 * 分页列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public PageInfo<PageData> listPage(PageData pd)throws Exception{
		PageHelper.startPage(pd.getInt("page"),pd.getInt("rows"));
		List<PageData> list = (List<PageData>) dao.findForList("UserMapper.listAll", pd);
		return new PageInfo(list);
	}

	/**
	 * 列表(全部)
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("UserMapper.listAll", pd);
	}

	/**
	 * 通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserMapper.findById", pd);
	}

	/**
	 * 批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("UserMapper.deleteAll", ArrayDATA_IDS);
	}


}


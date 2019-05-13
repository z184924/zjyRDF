package cn.zhangjingyao.service.system;

import cn.zhangjingyao.dao.DaoImpl;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
	public User loginUser(PageData pd)throws Exception{
		return (User)dao.findForObject("UserMapper.loginUser", pd);
	}

	/**
	 * 新增
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public boolean save(PageData pd)throws Exception{
		PageData account = (PageData) dao.findForObject("UserMapper.findByAccount", pd);
		if(account==null){
			dao.save("UserMapper.save", pd);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Boolean save(List<PageData> list)throws Exception{
		Boolean saveFlag=true;
		for (PageData pd:list) {
			PageData account = (PageData) dao.findForObject("UserMapper.findByAccount", pd);
			if(account==null){
				dao.save("UserMapper.save", pd);
			}else{
				saveFlag=false;
				break;
			}
		}
		if(!saveFlag){
			//手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return saveFlag;
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
	 * 分页查询
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public PageInfo<PageData> listPage(PageData pd)throws Exception{
		PageHelper.startPage(pd.getInt("pageNum"),pd.getInt("pageSize"));
		List<PageData> list = (List<PageData>) dao.findForList("UserMapper.listAll", pd);
		return new PageInfo(list);
	}

	/**
	 * 查询(全部)
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
	 * 通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findById(String userId)throws Exception{
		PageData pd = new PageData();
		pd.put("userId",userId);
		return (PageData)dao.findForObject("UserMapper.findById", pd);
	}

	/**
	 * 通过account获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByAccount(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserMapper.findByAccount", pd);
	}

	/**
	 * 通过account获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByAccount(String account)throws Exception{
		PageData pd = new PageData();
		pd.put("account",account);
		return (PageData)dao.findForObject("UserMapper.findByAccount", pd);
	}

	/**
	 * 批量删除
	 * @param arrayDataIds
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteAll(String[] arrayDataIds)throws Exception{
		dao.delete("UserMapper.deleteAll", arrayDataIds);
	}

}


package cn.zhangjingyao.service;

import cn.zhangjingyao.dao.DbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 */
@Service
public class DbService {
    @Autowired
    private DbDao dbDao;

    /**
     * 获取数据库名列表
     * @return
     */
    public List getDatabaseList(){
       return dbDao.getDatabaseList();
    }

    /**
     * 根据数据库名获取表名列表
     * @param dataBaseName 数据库名
     * @return
     */
    public List getTableList(String dataBaseName){
        return dbDao.getTableList(dataBaseName);
    }

    /**
     * 根据数据库名及表名获取列信息列表
     * @param dataBaseName
     * @param tableName
     * @return
     */
    public List getTableColumnList(String dataBaseName,String tableName){
        return dbDao.getTableColumnList(dataBaseName,tableName);
    }
}

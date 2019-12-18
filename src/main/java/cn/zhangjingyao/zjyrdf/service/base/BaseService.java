package cn.zhangjingyao.zjyrdf.service.base;

import cn.zhangjingyao.zjyrdf.dao.DaoImpl;
import cn.zhangjingyao.zjyrdf.entity.PageData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 */
public abstract class BaseService {

    @Autowired
    public DaoImpl dao;

    protected Logger logger = LogManager.getLogger(this.getClass());

    public Long getLastInsertId() {
        return (Long) dao.findForObject("BaseMapper.getLaseInsertId", new PageData());
    }
}

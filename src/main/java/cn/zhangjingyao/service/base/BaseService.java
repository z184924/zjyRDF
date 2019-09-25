package cn.zhangjingyao.service.base;

import cn.zhangjingyao.dao.DaoImpl;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.security.service.CustomTokenServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 */
public abstract class BaseService {

    @Autowired
    public CustomTokenServices customTokenServices;
    @Autowired
    public DaoImpl dao;

    protected Logger logger = LogManager.getLogger(this.getClass());

    public Long getLastInsertId() {
        return (Long) dao.findForObject("BaseMapper.getLaseInsertId", new PageData());
    }
}

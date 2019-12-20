package cn.zhangjingyao.zjyrdf.service.log;

import cn.zhangjingyao.zjyrdf.entity.PageData;
import cn.zhangjingyao.zjyrdf.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类名称:SystemLogService
 * 创建时间:2019-12-19
 *
 * @author CodeGenerator
 */
@org.springframework.stereotype.Service
public class SystemLogService extends BaseService {

    /**
     * 新增SystemLog
     *
     * @param pd PageData
     */
    public void save(PageData pd) {
        PageData maxTable = (PageData) dao.findForObject("LogIndexMapper.findMaxTable", pd);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        long beginTime = System.currentTimeMillis();
        if (pd.get("requestTime") != null) {
            beginTime = (long) pd.get("requestTime");
        }
        String beginTimeString = sdf.format(new Date(beginTime));
        String tableName = "log_" + pd.getString("systemTag") + "_" + beginTime;
        if (maxTable == null) {
            PageData newTableIndex = new PageData();
            newTableIndex.put("logTableName", tableName);
            newTableIndex.put("beginTime", beginTimeString);
            newTableIndex.put("systemTag", pd.getString("systemTag"));
            dao.save("LogIndexMapper.save", newTableIndex);
            dao.save("LogIndexMapper.createTable", tableName);
            pd.put("tableName", tableName);
        } else {
            pd.put("tableName", maxTable.getString("logTableName"));
            int maxId = 0;
            Object maxIdObject = dao.findForObject("SystemLogMapper.findMaxId", pd);
            if (maxIdObject != null) {
                maxId = (int) maxIdObject;
            }
            if (maxId >= 10 && !tableName.equals(maxTable.getString("logTableName"))) {
                PageData newTableIndex = new PageData();
                newTableIndex.put("logTableName", tableName);
                newTableIndex.put("beginTime", beginTimeString);
                newTableIndex.put("systemTag", pd.getString("systemTag"));
                dao.save("LogIndexMapper.save", newTableIndex);
                maxTable.put("endTime", beginTimeString);
                dao.update("LogIndexMapper.edit", maxTable);
                dao.save("LogIndexMapper.createTable", tableName);
            }
        }
        if (pd.get("requestTime") != null) {
            pd.put("requestTime", sdf.format(new Date((long) pd.get("requestTime"))));
        }
        if (pd.get("responseTime") != null) {
            pd.put("responseTime", sdf.format(new Date((long) pd.get("responseTime"))));
        }
        dao.save("SystemLogMapper.save", pd);
    }

    /**
     * 批量新增SystemLog
     *
     * @param list PageData List
     */
    public void save(List<PageData> list) {
        for (PageData pd : list) {
            save(pd);
        }
    }

    /**
     * 删除SystemLog
     *
     * @param pd PageData
     */
    public void delete(PageData pd) {
        dao.delete("SystemLogMapper.delete", pd);
    }

    /**
     * 批量删除SystemLog
     *
     * @param list PageData List
     */
    public void delete(List<PageData> list) {
        for (PageData pd : list) {
            dao.delete("SystemLogMapper.delete", pd);
        }
    }

    /**
     * 修改SystemLog
     *
     * @param pd PageData
     */
    public void edit(PageData pd) {
        dao.update("SystemLogMapper.edit", pd);
    }

    /**
     * 批量修改SystemLog
     *
     * @param list PageData List
     */
    public void edit(List<PageData> list) {
        for (PageData pd : list) {
            dao.update("SystemLogMapper.edit", pd);
        }
    }

    /**
     * 分页查询SystemLog
     *
     * @param pd PageData
     * @return PageInfo
     */
    public PageInfo<PageData> listPage(PageData pd) {
        PageHelper.startPage(pd.getInt("pageNum"), pd.getInt("pageSize"));
        List<PageData> pieceList = (List<PageData>) dao.findForList("LogIndexMapper.listAll", pd);
        List<String> logTableNameList = new ArrayList<>();
        for (PageData piece : pieceList) {
            logTableNameList.add(piece.getString("logTableName"));
        }
        List<PageData> list = (List<PageData>) dao.findForList("SystemLogMapper.listAllPiece", logTableNameList);
        return new PageInfo(list);
    }

    /**
     * 查询SystemLog(全部)
     *
     * @param pd PageData
     * @return PageData List
     */
    public List<PageData> listAll(PageData pd) {
        return (List<PageData>) dao.findForList("SystemLogMapper.listAll", pd);
    }

    /**
     * 通过id获取SystemLog数据
     *
     * @param pd PageData
     * @return PageData
     */
    public PageData findById(PageData pd) {
        return (PageData) dao.findForObject("SystemLogMapper.findById", pd);
    }

    /**
     * 批量删除SystemLog
     *
     * @param arrayDataIds Id数组
     */
    public void deleteAll(String[] arrayDataIds) {
        dao.delete("SystemLogMapper.deleteAll", arrayDataIds);
    }

}


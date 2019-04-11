package cn.zhangjingyao.util.toekn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author
 */

public class FormTokenPool extends ConcurrentHashMap<String,Long> {

    private Logger logger= LogManager.getLogger(this.getClass());
    private static final long EXPIRY_TIME=60*60*1000;
    private static FormTokenPool formTokenPool;
    private FormTokenPool() {}
    public static synchronized FormTokenPool getInstance() {
        if(formTokenPool==null) {
            formTokenPool=new FormTokenPool();
            formTokenPool.cleanPoolTask();
        }
        return formTokenPool;
    }

    public String addToken() {
        String uuid = this.get32UUID();
        formTokenPool.put(uuid,System.currentTimeMillis());
        return uuid;
    }

    public boolean checkToken(String uuid) {
        Long createTime = formTokenPool.get(uuid);
        if(createTime!=null){
            return true;
        }
        return false;
    }

    public boolean checkAndReomveToken(String uuid) {
        Long createTime = formTokenPool.get(uuid);
        if(createTime!=null){
            formTokenPool.remove(uuid);
            return true;
        }
        return false;
    }

    private boolean  cleanPoolTask() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                cleanPool();
            }
        };
        try {
            scheduledExecutorService.scheduleAtFixedRate(task,System.currentTimeMillis(), 24*60*60*1000, TimeUnit.MILLISECONDS);
        }catch (Exception e) {
            logger.error("FormTokenPool定时清理任务设置出现异常");
            return false;
        }
        return true;
    }

    public synchronized boolean cleanPool() {
        if(formTokenPool!=null) {
            logger.info("FormTokenPool Clean Start");
            Set<String> keySet = formTokenPool.keySet();
            for (String tokenStr : keySet) {
                Long createTime = formTokenPool.get(tokenStr);
                if(System.currentTimeMillis()>createTime+EXPIRY_TIME) {
                    formTokenPool.remove(tokenStr);
                }
            }
        }
        return true;
    }

    private String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}

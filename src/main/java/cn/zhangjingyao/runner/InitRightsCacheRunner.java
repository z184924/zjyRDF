package cn.zhangjingyao.runner;

import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.util.RightsCache;
import cn.zhangjingyao.service.system.RightsService;
import cn.zhangjingyao.util.toekn.FormTokenPool;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限列表启动缓存
 *
 * @author
 */
@Component
public class InitRightsCacheRunner implements ApplicationRunner {
    @Autowired
    private RightsService rightsService;

    @Override
    public void run(ApplicationArguments args) {
        List<PageData> rightsList = rightsService.listAll(new PageData());
        RightsCache rightsCache = RightsCache.getInstance();
        for (PageData rights : rightsList) {
            rightsCache.put(rights.getString("url"), rights.getInt("rightsId"));
        }
    }

}
